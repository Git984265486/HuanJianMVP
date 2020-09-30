package com.org.huanjianmvp.Contrast;

import android.content.Context;

import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 契约接口，规定行为，管理M层、V层、P层的抽象方法
 * 降低耦合度
 * Created by Administrator on 2020/8/19.
 */

public interface LoginContrast {

    /**【 Model 层的方法接口】**/
    interface Model{
        //登录验证
        void validateLogin(String userName , String passWord , Context context) throws Exception;

        //处理退出请求
        void exitAction(SweetAlertDialog alert);

        //版本号获取
        void versionAction(Context context);

        //权限申请处理
        void permissionAction(Context context);

        //token刷新处理
        void tokenAction();
    }

    /**【 View 层和 Presenter 层的方法接口】**/
    interface ViewAndPresenter{

        //登录请求
        void requestLogin(String userName , String passWord , Context context);

        //响应请求结果
        void responseResult(String msg);

        //请求退出
        void requestExit(SweetAlertDialog alert);

        //响应请求退出
        void responseExit(Boolean isExit);

        //请求版本号
        void requestVersion(Context context);

        //响应版本号
        void responseVersion(Map<String,String> map);

        //权限申请
        void requestPermission(Context context);

        //请求刷新token
        void requestToken();
    }

}
