package com.example.administrator.happygame.activity.fifth;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import com.example.administrator.happygame.Listener.MyDragListener;
import com.example.administrator.happygame.PoiIntent;
import com.example.administrator.happygame.PoiOverlay;
import com.example.administrator.happygame.R;
import com.example.administrator.happygame.RecyclerAdapter1;
import com.example.administrator.happygame.SuggestionPoi;
import com.example.administrator.happygame.util.BitmapUtil;
import com.example.administrator.happygame.util.MyApplication;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.direction;


public class MapActivity extends AppCompatActivity implements BaiduMap.OnMarkerClickListener, OnGetSuggestionResultListener, OnGetPoiSearchResultListener {
    private static final int PoiSearchMode_10Poi = 1;
    private static final int PoiSearchMode_1Poi = 2;
    final int RequestCode = 1;
    private final int Message_Address = 1;
    TextView Textview_SearchType;
    OnGetPoiSearchResultListener listener;
    private LocationClient mlocationClient;
    private MyLocationListener mlocationListener;
    private BaiduMap baiduMap;
    private MapView mapView;
    private boolean isFirstTime = true;
    private PoiSearch poiSearch;
    private BDLocation MyLocation = null;
    private LatLng SetPosition;
    private Marker SetPositionMarker;
    private SuggestionSearch suggestionSearch = null;
    private ArrayAdapter SuggestionWordsAdapter = null;
    private List<SuggestionPoi> suggestionPois;
    private AutoCompleteTextView autoCompleteTextView;
    private SuggestionPoi SetPositionPoi = null; //这个就是被选中地点的详细资料！！
    private LatLng Mylatlng = null;
    private int PoiSearchMode = 1;
    private Intent StartIntent;
    private PoiIntent StartData;
    private PoiOverlay overlay;
    private EditText edittext_fromcity;
    private RecyclerAdapter1 recyclerAdapter1;

    private TextWatcher AutoTextWatch = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (charSequence.length() <= 0) {
                return; //文字为0没有反应！！
            }
            Log.d("PoiSearch", "onTextChanged");
            suggestionSearch.requestSuggestion(new SuggestionSearchOption().city(MyLocation.getCity().toString()).keyword(charSequence.toString()).citylimit(true));  //city限定只能在该城市内找到兴趣点！！！
            //在回调函数的时候，要获得联想词的列表，并更新！！
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initBeforCreatView();

        setContentView(R.layout.activity_map);


        Textview_SearchType = (TextView) findViewById(R.id.Text_type);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.editWhere);


        mapView = (MapView) findViewById(R.id.MapView);
        baiduMap = mapView.getMap();
        baiduMap.setMyLocationEnabled(true);//要设置中心点所以要设置好！！
        baiduMap.setOnMarkerClickListener(this);//设点击事件！！
        baiduMap.setOnMarkerDragListener(new MyDragListener(getApplicationContext()));
        suggestionSearch = SuggestionSearch.newInstance();
        suggestionSearch.setOnGetSuggestionResultListener(this);

        poiSearch = PoiSearch.newInstance();
        poiSearch.setOnGetPoiSearchResultListener(this);


        baiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                //
                //  Toast.makeText(MapActivity.this,"Name:"+mapPoi.getName(),Toast.LENGTH_SHORT).show();
                // 点击poi
                //点击兴趣点！！
                TextView textView = new TextView(MapActivity.this);
                textView.setText("" + mapPoi.getName());
                textView.setTextSize(12);
                textView.setGravity(Gravity.CENTER);
                final InfoWindow infoWindow = new InfoWindow(textView, mapPoi.getPosition(), 0);
                baiduMap.showInfoWindow(infoWindow);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        baiduMap.hideInfoWindow();
                    }
                });
                return false;
            }

            @Override
            public void onMapClick(LatLng latLng) {
                //  Toast.makeText(MapActivity.this,"X:"+latLng.latitude+"\tY:"+latLng.longitude,Toast.LENGTH_SHORT).show();
          /*      GeoCoder mSearch = GeoCoder.newInstance();//初始化地理编码
                ReverseGeoCodeOption reverseGeoCodeOption = new ReverseGeoCodeOption(); //解析GeoLocation获取地址！！
                reverseGeoCodeOption.location(latLng);//设置坐标点
                mSearch.setOnGetGeoCodeResultListener(new MyGeoListener()); ///你要先listener才能分析东西！！！
                mSearch.reverseGeoCode(reverseGeoCodeOption);//将坐标点转换为地址信息*/
            }
        });

        /////////////////
                   /*  这是button！！设置搜索地址！！ */
        Button GetButton = (Button) findViewById(R.id.Button_get);
        GetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("".equals(autoCompleteTextView.getText())) {
                    Toast.makeText(MapActivity.this, "请输入搜索目标", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (recyclerAdapter1.getChooseInt() == 0) {
                    String text = autoCompleteTextView.getText().toString();
                    Toast.makeText(MapActivity.this, "" + text, Toast.LENGTH_SHORT).show();
                    if (SetPositionPoi != null) //每次搜索地点，把就地点的marker清空掉
                    {
                        SetPositionMarker.remove(); //remove只是显示消失，并没有为空值
                        Log.d("SetPositionMarker", "" + SetPositionMarker);
                    }
                    for (SuggestionPoi poi : suggestionPois)     //遍历推荐栏
                    {
                        if (text.equals(poi.getKey()))  // 确定文本之后，去推荐栏获取 文本相同名字的poi信息！！，目的是获取文本所指的poi信息
                        {
                            SetPositionPoi = poi;
                            SuggestionResult.SuggestionInfo Targetinfo = SetPositionPoi.getInfo(); //info有poi的信息
                            MapStatus.Builder builder = new MapStatus.Builder().target(Targetinfo.pt).zoom(18.0f); //把镜头移向它
                            baiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
                            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.seticon_baiduicon);
                            BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(BitmapUtil.ZoomBitmap(bitmap, 60, 60));
                            //在搜索点出现标志！！ 可以用蓝色标志
                            SetPositionMarker = (Marker) baiduMap.addOverlay(new MarkerOptions().position(Targetinfo.pt).icon(bitmapDescriptor).perspective(true));
                            SetPositionMarker.setZIndex(999);
                            SetPositionMarker.setTitle(Targetinfo.key);//可以设置要显示的文本
                            break;
                        }
                    }
                } else if (recyclerAdapter1.getChooseInt() == 1) {
                    String text = autoCompleteTextView.getText().toString();
                    Toast.makeText(MapActivity.this, "" + text, Toast.LENGTH_SHORT).show();
                    //获得文本 开启城市搜索！！
                    PoiCitySearchOption CitybySearchOption = new PoiCitySearchOption(); //POI附近检索参数设置类
                    CitybySearchOption.keyword(text);//搜索关键字，比如：银行、网吧、餐厅等
                    CitybySearchOption.city(MyLocation.getCity());
                    //  CitybySearchOption.isReturnAddr(true);
                    CitybySearchOption.pageCapacity(10);
                    poiSearch.searchInCity(CitybySearchOption);
                }
                BitmapUtil.hideKeyBoard(getApplicationContext(), autoCompleteTextView);
            }
        });


        //选择类型 textview点击事项


        initPoiType();

        //


        //点击定位按钮！！！
        Button editText2 = (Button) findViewById(R.id.Button_SetLocation);
        editText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setreturnresult();
                finish();
            }
        });


        if (StartData != null) {
            MapStatus.Builder builder = new MapStatus.Builder().target(new LatLng(StartData.getLatt(), StartData.getLongt())).zoom(18.0f); //把镜头移向它
            baiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.seticon_baiduicon);
            BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(BitmapUtil.ZoomBitmap(bitmap, 60, 60));
            //在搜索点出现标志！！ 可以用蓝色标志
            SetPositionMarker = (Marker) baiduMap.addOverlay(new MarkerOptions().position(new LatLng(StartData.getLatt(), StartData.getLongt())).icon(bitmapDescriptor).perspective(true));
            SetPositionMarker.setZIndex(999);
            SetPositionMarker.setTitle(StartData.getName());//可以设置要显示的文本
            //从这里恢复原有的点！并定位把
        }


                /* 这里是对周边的poi就行 图标化显示！！ */


        initAutoCompleteTextView();







                /*这里是寻求权限！！*/
        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(MapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);//获得 精确 定位 ！！
        }
        if (ContextCompat.checkSelfPermission(MapActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (ContextCompat.checkSelfPermission(MapActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(MapActivity.this, permissions, RequestCode);
        } else {
            requestLocation();
        }


    }


    private void initAutoCompleteTextView() {
        //这是对扩充文本autoview
        autoCompleteTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("地点搜索".equals(Textview_SearchType.getText())) {
                    String text = ((AutoCompleteTextView) view).getText().toString();
                    if (text.length() <= 0) {
                        return; //文字为0没有反应！！
                    }
                    Log.d("PoiSearch", "onTextChanged");
                    suggestionSearch.requestSuggestion(new SuggestionSearchOption().city(MyLocation.getCity().toString()).keyword(text).citylimit(true));  //city限定只能在该城市内找到兴趣点！！！
                    //在回调函数的时候，要获得联想词的列表，并更新！！
                }
            }
        });

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case RequestCode:
                if (grantResults.length > 0) {
                    for (int result : grantResults) //grantReults 允许的结果都是一组组的！
                    {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(MapActivity.this, "必须所有权限都被允许才能打开app", Toast.LENGTH_SHORT).show();
                            finish();//关闭app
                            return;
                        }
                    } /* 所有权限ok了就可以请求定位！！ */
                    requestLocation();
                } else {
                    Toast.makeText(this, "发生未知错误", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }

    private void requestLocation() {
        initLocationOption();
        mlocationClient.start(); // 这个就启动locationClient里面的 获取定位！
    }

    private void initLocationOption() {

        LocationClientOption locationClientOption = new LocationClientOption();
        locationClientOption.setNeedDeviceDirect(true);
        locationClientOption.setCoorType("bd0911");
        locationClientOption.setScanSpan(1000);
        locationClientOption.setLocationNotify(true);
        locationClientOption.setIsNeedAddress(true);//精确到街道
        locationClientOption.setIsNeedLocationDescribe(true);
        locationClientOption.setIsNeedLocationDescribe(true);// 对定位的位置的描述！！
        locationClientOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        mlocationClient.setLocOption(locationClientOption);

    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        if (SetPositionMarker != null) {
            if (marker.getZIndex() == SetPositionMarker.getZIndex()) {
                Toast.makeText(MapActivity.this, "店名：" + SetPositionMarker.getTitle(), Toast.LENGTH_SHORT).show();
            }
        }

        return false;
    }

    @Override  //这个是被回调的方法！！
    public void onGetSuggestionResult(SuggestionResult suggestionResult) {

        //这个poi的提示词是一个list的！！
        if (suggestionResult == null || suggestionResult.getAllSuggestions() == null) //要么没有list不行要么没有返回值！！
        {
            return;
        }
        //每一次回调这个函数都要更新list，防止联想词语串了
        suggestionPois = new ArrayList<>();
        for (SuggestionResult.SuggestionInfo info : suggestionResult.getAllSuggestions()) {

            if (info.key != null) {
                Log.d("PoiSearch", info.pt + "");//这里显示为0 **********************要查查是什么情况
                SuggestionPoi poi = new SuggestionPoi(info.key, info);
                suggestionPois.add(poi);
                Log.d("PoiSearch", "" + info.key);//这个key就是兴趣点的名字
            }
        }
        //
        List<String> WordList = new ArrayList<>();
        for (SuggestionPoi poi : suggestionPois) {
            WordList.add(poi.getKey());
        }
        //
        SuggestionWordsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, WordList); //
        autoCompleteTextView.setAdapter(SuggestionWordsAdapter);
        SuggestionWordsAdapter.notifyDataSetChanged(); //注意到数据更换！！
        Log.d("PoiSearch", "" + "onGetSuggestionResult");

    }

    @Override
    public void onGetPoiResult(PoiResult poiResult) {
        if (poiResult.error == SearchResult.ERRORNO.NO_ERROR) {

            Log.d("POIIOP", "OK");

            switch (PoiSearchMode) {
                case PoiSearchMode_10Poi:
                    if (overlay != null) {
                        overlay.removeFromMap(); //把一次的搜索结果清除了！
                    }
                    overlay = new MyPoiOverlay(baiduMap, getApplicationContext());
                    List<PoiInfo> allAddr = poiResult.getAllPoi();
                    for (PoiInfo info : allAddr) {
                        Log.d("POIIOP", info.address);
                        Log.d("POIIOP", info.name);
                    }

                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_marka); //这个可以替换icon，检索周边的！！！

                    BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(BitmapUtil.ZoomBitmap(bitmap, 60, 90));
                    baiduMap.setOnMarkerClickListener(overlay);
                    overlay.setPoiIcon(bitmapDescriptor);
                    overlay.setData(poiResult);
                    overlay.addToMap();
                    overlay.zoomToSpan();
                    //这里可以制作障碍物！！
                    break;
                case PoiSearchMode_1Poi:
                    Toast.makeText(MapActivity.this, poiResult.getAllPoi().get(0).address, Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

    }

    @Override
    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

    }

    private void initPoi(BDLocation bdLocation) {


    }

    private void ToCreatMyLocation(BDLocation bdLocation) {

        MyLocationData myLoaction = new MyLocationData.Builder().latitude(bdLocation.getLatitude()).longitude(bdLocation.getLongitude()).accuracy(100).direction(direction).build();
        //accyracy 是 点四周的蓝色区域！！
        baiduMap.setMyLocationData(myLoaction);

        Mylatlng = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());

        //这样就不能实现动态跟随！
        Log.d("Are you Ok", "");
    }

    @Override
    protected void onStop() {
        super.onStop();
        mlocationClient.unRegisterLocationListener(mlocationListener);
        mlocationClient.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        baiduMap.setMyLocationEnabled(false);
        poiSearch.destroy();

    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onBackPressed() {
        // SetReturnResult();
        super.onBackPressed();
    }

    public void setreturnresult() {
        Intent intent = new Intent();
        Boolean isSetPosition = false;
        //首先要判断究竟有没有选择到地点
        if (SetPositionPoi != null) {
            isSetPosition = true;
            SuggestionResult.SuggestionInfo info = SetPositionPoi.getInfo();
            //从这里获取信息返回去！！
      /*          ReverseGeoCodeOption  option =new ReverseGeoCodeOption();
                option.location(info.pt);
                GeoCoder one = GeoCoder.newInstance();
                one.setOnGetGeoCodeResultListener(new MyGeoListener());
                one.reverseGeoCode(option);*/


            PoiIntent poiIntent = new PoiIntent();
            poiIntent.setAddress(info.city + info.district);
            poiIntent.setName(info.key);
            poiIntent.setLatt(info.pt.latitude);
            poiIntent.setLongt(info.pt.longitude);
            //
            /*    PoiNearbySearchOption option = new PoiNearbySearchOption();
                option.location(info.pt).keyword(info.key).pageNum(1);
                PoiSearchMode = PoiSearchMode_1Poi ; //调整！
                poiSearch.searchNearby(option);*/

            intent.putExtra("SetPosition", poiIntent);
        }
        intent.putExtra("isSetPosition", isSetPosition); //检索有没有设置游戏点
        setResult(RESULT_OK, intent);

    }

    private void initPoiType() {
        autoCompleteTextView.addTextChangedListener(AutoTextWatch);
        /*点击文本 选择方式，检索方式！-------------------------*/
        Textview_SearchType.setText("地点搜索");
        List<String> dataList = new ArrayList<>();
        dataList.add("地点搜索");
        dataList.add("周边搜索");
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.listviewitem, null);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.Recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerAdapter1 = new RecyclerAdapter1(dataList, Textview_SearchType);
        recyclerView.setAdapter(recyclerAdapter1);
        recyclerView.setLayoutManager(layoutManager);
        Textview_SearchType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (recyclerView.getVisibility() == View.GONE) {
                    recyclerView.setVisibility(View.VISIBLE);
                } else if (recyclerView.getVisibility() == View.VISIBLE) {
                    recyclerView.setVisibility(View.GONE);
                    BitmapUtil.hideKeyBoard(getApplicationContext(), autoCompleteTextView);
                }
            }
        });
        /*----------------------------------*/
        /*--------------------Autocompleteview 把信息扩展取消掉！！！！！！*/
        Textview_SearchType.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                recyclerView.setVisibility(View.GONE);
                if ("地点搜索".equals(charSequence.toString())) {
                    autoCompleteTextView.addTextChangedListener(AutoTextWatch);
                    Toast.makeText(MapActivity.this, "DiDian" + "ADD", Toast.LENGTH_SHORT).show();
                } else if ("周边搜索".equals(charSequence.toString())) {
                    autoCompleteTextView.removeTextChangedListener(AutoTextWatch);
                    Toast.makeText(MapActivity.this, "ZhouBian" + "remove", Toast.LENGTH_SHORT).show();
                    //周边没有了自动的东西
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
      /*----------------------------------------- */
    }

    private void initBeforCreatView() {
        StartIntent = getIntent();
        if (StartIntent.getBooleanExtra("isSetPosition", false)) {
            StartData = (PoiIntent) StartIntent.getSerializableExtra("SetPosition");
        }

        mlocationListener = new MyLocationListener(); //LocationListener 对位置定位类
        //MyLocation
        mlocationClient = new LocationClient(getApplicationContext());//这个位置委托人只需要 Context就可以获得势力了
        //位置委托类，
        mlocationClient.registerLocationListener(mlocationListener);
        SDKInitializer.initialize(MyApplication.getContext());
    }

    class MyLocationListener implements BDLocationListener {
        /*  在这个类里面的 onReceiveLocation（在接受位置！）去处理位置信息  */
        @Override
        public void onReceiveLocation(final BDLocation bdLocation) {

            MyLocation = bdLocation; //全局变量!
            Log.d("Poi1", "Doit1");
            if (bdLocation.getLocType() == BDLocation.TypeGpsLocation || bdLocation.getLocType() == BDLocation.TypeNetWorkLocation) {
                Log.d("Poi1", "Doit");
                if (isFirstTime) {
                    //第一次的时候把镜头拉过去！
                    MapStatus.Builder builder = new MapStatus.Builder();
                    builder.zoom(18f).target(new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude()));
                    baiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
                    // }
                    //每次开始前清空mbaidu的标志物！！ 其实只需要在检索地点后从新搜查就好了，要刷新的只是自己位置
                    ToCreatMyLocation(bdLocation);
                    initPoi(bdLocation);
                    isFirstTime = false;
                }

            }


        }

    }

    class MyGeoListener implements OnGetGeoCoderResultListener {

        private SuggestionPoi poi;

        public SuggestionPoi getPoi() {
            return poi;
        }

        public void setPoi(SuggestionPoi poi) {
            this.poi = poi;
        }

        @Override
        public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
            if (geoCodeResult == null || geoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) //这个是处理没有找到地址的！！
            {
                //在没有找到地址的地方！！，要让 SetPosition为null
                SetPosition = null;
                Toast.makeText(MapActivity.this, "没有找到该地址！,", Toast.LENGTH_SHORT).show();
            } else {

            }
        }

        @Override
        public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
            //这个是反编制！！
            //根据Latlng来获取地址信息！！！String address = geoCodeResult.getAddress() ;
/*
            address =  reverseGeoCodeResult.getAddress()  ;
            Toast.makeText(MapActivity.this,""+address ,Toast.LENGTH_SHORT).show();
*/

        }
    }

    class MyPoiOverlay extends PoiOverlay {
        /**
         * 构造函数
         *
         * @param baiduMap 该 PoiOverlay 引用的 BaiduMap 对象
         */

        private Context MyContext;

        public MyPoiOverlay(BaiduMap baiduMap, Context context) {
            super(baiduMap);
            MyContext = context;
        }

        @Override
        public boolean onPoiClick(int i) {

            PoiInfo one = getPoiResult().getAllPoi().get(i);

            Toast.makeText(MapActivity.this, one.name, Toast.LENGTH_SHORT).show();

            autoCompleteTextView.setText(one.name);


            return true;
        }
    }


}

