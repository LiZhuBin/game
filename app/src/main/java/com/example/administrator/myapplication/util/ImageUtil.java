package com.example.administrator.myapplication.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2017/8/23 0023.
 */

public class ImageUtil {
    /**
     * 调用系统自带裁图工具
     *
     * @param activity
     * @param size
     * @param uri
     * @param action
     * @param cropFile
     */
    public static void cropPicture(Activity activity, int size, Uri uri, int action, File cropFile) {
        try {
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(uri, "image/*");
            // 返回格式
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
            intent.putExtra("crop", true);
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("outputX", size);
            intent.putExtra("outputY", size);
            intent.putExtra("scale", true);
            intent.putExtra("scaleUpIfNeeded", true);
            intent.putExtra("cropIfNeeded", true);
            intent.putExtra("return-data", false);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(cropFile));
            activity.startActivityForResult(intent, action);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 调用系统自带裁图工具
     * outputX = 250
     * outputY = 250
     *
     * @param activity
     * @param uri
     * @param action
     * @param cropFile
     */
    public static void cropPicture(Activity activity, Uri uri, int action, File cropFile) {
        cropPicture(activity, 250, uri, action, cropFile);
    }

    /**
     * 调用系统自带裁图工具
     * 并保存文件
     * outputX = 250
     * outputY = 250
     *
     * @param activity
     * @param uri
     * @param action
     * @param appName
     * @param application
     * @return
     */
    public static File cropPicture(Activity activity, Uri uri, int action, String appName, Application application) {
        File resultFile = createImageFile(appName, application);
        cropPicture(activity, 250, uri, action, resultFile);
        return resultFile;
    }

    /**
     * 创建图片文件
     *
     * @param appName
     * @param application
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static File createImageFile(String appName, Application application) {
        File folder = createImageFileInCameraFolder(appName, application);
        String filename = new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis());
        return new File(folder, filename + ".jpg");
    }

    /**
     * 创建图片文件夹
     *
     * @param appName
     * @param application
     * @return
     */
    public static File createImageFileInCameraFolder(String appName, Application application) {
        String folder = ImageUtil.createAPPFolder(appName, application);
        File file = new File(folder, "image");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    /**
     * 创建App文件夹
     *
     * @param appName
     * @param application
     * @return
     */
    public static String createAPPFolder(String appName, Application application) {
        return createAPPFolder(appName, application, null);
    }

    /**
     * 创建App文件夹
     *
     * @param appName
     * @param application
     * @param folderName
     * @return
     */
    public static String createAPPFolder(String appName, Application application, String folderName) {
        File root = Environment.getExternalStorageDirectory();
        File folder;
        /**
         * 如果存在SD卡
         */
        if ( root != null) {
            folder = new File(root, appName);
            if (!folder.exists()) {
                folder.mkdirs();
            }
        } else {
            /**
             * 不存在SD卡，就放到缓存文件夹内
             */
            root = application.getCacheDir();
            folder = new File(root, appName);
            if (!folder.exists()) {
                folder.mkdirs();
            }
        }
        if (folderName != null) {
            folder = new File(folder, folderName);
            if (!folder.exists()) {
                folder.mkdirs();
            }
        }
        return folder.getAbsolutePath();
    }
}
