package com.example.administrator.happygame.util;

import android.app.ProgressDialog;
import android.content.Context;

import com.example.administrator.happygame.R;

/**
 * Created by Administrator on 2017/9/20 0020.
 */

public class ProgressDlgUtil {
    static ProgressDialog progressDlg = null;

    public static void showProgressDlg(String strMessage, Context ctx) {

        if (null == progressDlg) {
            progressDlg = new ProgressDialog(ctx);
            //设置进度条样式
            progressDlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            //设置进度条标题
            progressDlg.setTitle(com.example.administrator.happygame.util.ApplicationUtil.getContext().getString(
                    R.string.app_name));
            //提示的消息
            progressDlg.setMessage(strMessage);
            progressDlg.setIndeterminate(false);
            progressDlg.setCancelable(false);
            progressDlg.setIcon(R.drawable.image_scrolling_head);
            progressDlg.show();
        }
    }

    /**
     * 结束进度条
     */
    public static void stopProgressDlg() {
        if (null != progressDlg) {
            progressDlg.dismiss();
            progressDlg = null;
        }
    }
}