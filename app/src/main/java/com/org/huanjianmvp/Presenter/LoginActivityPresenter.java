package com.org.huanjianmvp.Presenter;

import android.content.Context;

import com.org.huanjianmvp.Base.BaseActivityPresenter;
import com.org.huanjianmvp.Contrast.LoginContrast;
import com.org.huanjianmvp.Login;
import com.org.huanjianmvp.Model.LoginActivityModel;

import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Administrator on 2020/8/19.
 */

public class LoginActivityPresenter extends BaseActivityPresenter<Login,LoginActivityModel,LoginContrast.ViewAndPresenter> {


    @Override
    public LoginContrast.ViewAndPresenter getContract() {
        return new LoginContrast.ViewAndPresenter() {
            @Override
            public void requestLogin(String userName, String passWord , Context context) {
                try {
                    mModel.getContract().validateLogin(userName , passWord , context);
                } catch (Exception e) {
                    weakView.get().responseError(userName,e);
                    e.printStackTrace();
                }
            }

            @Override
            public void responseResult(String msg) {
                weakView.get().getContract().responseResult(msg);
            }

            @Override
            public void requestExit(SweetAlertDialog alert) {
                mModel.getContract().exitAction(alert);
            }

            @Override
            public void responseExit(Boolean isExit) {
                weakView.get().getContract().responseExit(isExit);
            }

            @Override
            public void requestVersion(Context context) {
                mModel.getContract().versionAction(context);
            }

            @Override
            public void responseVersion(Map<String,String> map) {
                weakView.get().getContract().responseVersion(map);
            }

            @Override
            public void requestPermission(Context context) {
                mModel.getContract().permissionAction(context);
            }

            @Override
            public void requestToken() {
                try {
                    mModel.getContract().tokenAction();
                }catch (Exception e){
                    weakView.get().responseError(e,e);
                }
            }

        };
    }

    @Override
    public LoginActivityModel createModel() {
        return new LoginActivityModel(this);
    }
}
