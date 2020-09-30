package com.org.huanjianmvp.Utils;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.mingle.widget.LoadingView;
import com.org.huanjianmvp.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Administrator on 2020/8/5.
 * 弹窗工具类
 */

public class AlertDialogUtils {

    private Context context;
    private SweetAlertDialog alertDialog;


    public AlertDialogUtils(Context context){
        this.context = context;
    }

    /**【标题 弹框】**/
    public void AlertTitle(String title , String type){
        if (type.equals("warning")){            //警告框
            alertDialog = new SweetAlertDialog(context , SweetAlertDialog.WARNING_TYPE);
        }else if (type.equals("error")){        //错误框
            alertDialog = new SweetAlertDialog(context , SweetAlertDialog.ERROR_TYPE);
        }else if (type.equals("progress")){     //加载中
            alertDialog = new SweetAlertDialog(context , SweetAlertDialog.PROGRESS_TYPE);
            alertDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            alertDialog.setCancelable(true);
        }else {                                 //成功框
            alertDialog = new SweetAlertDialog(context , SweetAlertDialog.SUCCESS_TYPE);
        }
        alertDialog.setConfirmText("关闭");
        alertDialog.setTitleText(title);
        alertDialog.show();
    }

    /**【标题 + 内容 弹框】**/
    public void AlertTitleAndMessage(String title , String message , String type){
        if (type.equals("warning")){
            alertDialog = new SweetAlertDialog(context , SweetAlertDialog.WARNING_TYPE);
        }else if (type.equals("error")){
            alertDialog = new SweetAlertDialog(context , SweetAlertDialog.ERROR_TYPE);
        }else{
            alertDialog = new SweetAlertDialog(context , SweetAlertDialog.SUCCESS_TYPE);
        }
        alertDialog.setTitleText(title);
        alertDialog.setContentText(message);
        alertDialog.setConfirmText("关闭");
        alertDialog.show();
    }

    /**【获取一个弹框对象】**/
    public SweetAlertDialog getAlertDialog(String type){
        if (type.equals("warning")){
            alertDialog = new SweetAlertDialog(context , SweetAlertDialog.WARNING_TYPE);
        }else if (type.equals("error")){
            alertDialog = new SweetAlertDialog(context , SweetAlertDialog.ERROR_TYPE);
        }else if (type.equals("progress")){
            alertDialog = new SweetAlertDialog(context , SweetAlertDialog.PROGRESS_TYPE);
            alertDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            alertDialog.setCancelable(true);
        }else{
            alertDialog = new SweetAlertDialog(context , SweetAlertDialog.SUCCESS_TYPE);
        }
        alertDialog.setConfirmText("关闭");
        return alertDialog;
    }

    /**【确认后切换弹框样式】**/
    public void AlertDialogChangeType(String title , String message , String type){
        if (type.equals("warning")){
            alertDialog = new SweetAlertDialog(context , SweetAlertDialog.WARNING_TYPE);
            alertDialog.setCancelText("取消");
            alertDialog.setConfirmText("确定");
            alertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sDialog) {
                    sDialog.setTitleText("操作成功!");
                    sDialog.setConfirmText("完成");
                    sDialog.setConfirmClickListener(null);
                    sDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                }
            });
        }else if (type.equals("error")){
            alertDialog = new SweetAlertDialog(context , SweetAlertDialog.ERROR_TYPE);
            alertDialog.setCancelText("取消");
            alertDialog.setConfirmText("确定");
            alertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sDialog) {
                    sDialog.setTitleText("操作成功!");
                    sDialog.setConfirmText("完成");
                    sDialog.setConfirmClickListener(null);
                    sDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                }
            });
        }else{
            alertDialog = new SweetAlertDialog(context , SweetAlertDialog.SUCCESS_TYPE);
            alertDialog.setCancelText("取消");
            alertDialog.setConfirmText("确定?");
            alertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sDialog) {
                    sDialog.setTitleText("操作成功!");
                    sDialog.setConfirmText("完成");
                    sDialog.setConfirmClickListener(null);
                    sDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                }
            });
        }
        alertDialog.setTitleText(title);
        alertDialog.setContentText(message);
        alertDialog.setConfirmText("确定");
        alertDialog.show();
    }

    /**【关闭弹框】**/
    public void dismissDialog(){
        if (alertDialog!=null){
            alertDialog.dismiss();
        }
    }

    public static SweetAlertDialog showProgressDialog(final Context context){
        SweetAlertDialog dialog = new SweetAlertDialog(context , SweetAlertDialog.PROGRESS_TYPE);
        dialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        dialog.setCancelable(false);
        //dialog.setContentText("数据加载中");
        dialog.setTitleText("数据加载中");
        return dialog;
    }

    public static void dismissAlertDialog(SweetAlertDialog dialog){
        if (dialog != null){
            dialog.dismiss();
        }
    }

    /**【加载框展示】**/
    public static AlertDialog AlertLoading(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        AlertDialog dialog = builder.create();
        View dialogView = View.inflate(context, R.layout.loading,null);   //自定义弹框视图
        LoadingView loadingView = dialogView.findViewById(R.id.loadView);
        loadingView.setLoadingText("数据加载中. . .");
        dialog.setView(dialogView);
        dialog.getWindow().setDimAmount(0f);    //去掉遮罩层
        Window window = dialog.getWindow();
        WindowManager.LayoutParams layout = window.getAttributes();
        layout.alpha = 0.8f;
        window.setAttributes(layout);       //弹窗透明度设置
        dialog.setCancelable(false);        //点击弹框外部无法取消
        return dialog;
    }

    /**【加载框关闭】**/
    public static void dismissLoading(AlertDialog dialog){
        if (dialog != null){
            dialog.dismiss();
        }
    }
}
