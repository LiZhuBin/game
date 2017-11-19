package com.example.administrator.happygame.activity.fifth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.administrator.happygame.R;
import com.example.administrator.happygame.base.BaseActivity;
import com.example.administrator.happygame.util.LogUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EditActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
String data;
    String type;
    @Bind(R.id.has_change_data)
    EditText hasChangeData;
    int origin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ButterKnife.bind(this);
        type = getIntent().getExtras().getString("type");
        data=getIntent().getExtras().getString("DATA");
        hasChangeData.setText(data);
        hasChangeData.setSelection(hasChangeData.getText().length());
        if ("name".equals(type)) {
            toolbar.setTitle("修改名字");
            origin = 2;
        } else if ("signature".equals(type)) {
            toolbar.setTitle("修改签名");
            origin = 3;
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.ensure:
                Intent intent = new Intent();
                LogUtil.e(hasChangeData.getText().toString());
                intent.putExtra("data", hasChangeData.getText().toString());
                EditActivity.this.setResult(origin, intent);
                EditActivity.this.finish();
                break;
            default:
                break;
        }
        return true;
    }


}
