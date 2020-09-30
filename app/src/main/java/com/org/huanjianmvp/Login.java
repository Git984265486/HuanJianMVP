package com.org.huanjianmvp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.org.huanjianmvp.Activity.ListDatas;
import com.org.huanjianmvp.Activity.Setting;
import com.org.huanjianmvp.Base.BaseActivity;
import com.org.huanjianmvp.Contrast.LoginContrast;
import com.org.huanjianmvp.Domain.validateCode;
import com.org.huanjianmvp.Internet.ObserverManager;
import com.org.huanjianmvp.Internet.RetrofitManager;
import com.org.huanjianmvp.Presenter.LoginActivityPresenter;
import com.org.huanjianmvp.Utils.AlertDialogUtils;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.Observable;

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 仅负责展示处理，不涉及逻辑以及数据处理
 *
 * **/
public class Login extends BaseActivity<LoginActivityPresenter,LoginContrast.ViewAndPresenter> {

    private String username , password;
    private MaterialEditText userName , passWord;
    private BootstrapButton btnLogin , btnExit , btnSetting;
    private AlertDialogUtils dialogUtils;
    private SweetAlertDialog alert;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private TextView appVersionText;


    private AlertDialog dialog;

    /**【实现契约接口中的方法】**/
    @Override
    public LoginContrast.ViewAndPresenter getContract() {
        return new LoginContrast.ViewAndPresenter() {
            /**【发起登录请求】**/
            @Override
            public void requestLogin(String userName, String passWord , Context context) {
                mPresenter.getContract().requestLogin(userName,passWord , context);
            }
            /**【响应请求结果】**/
            @Override
            public void responseResult(String msg) {
                if (msg != null){
                    if (msg.equals("登录成功")){
                        Intent intent = new Intent(Login.this, ListDatas.class);
                        startActivity(intent);
                        Login.this.finish();
                    }else{
                        dialogUtils.AlertTitle(msg,"warning");
                    }
                }
            }

            @Override
            public void requestExit(SweetAlertDialog alert) {
                mPresenter.getContract().requestExit(alert);
            }

            @Override
            public void responseExit(Boolean isExit) {
                if (isExit){
                    Login.this.finish();
                }
            }

            @Override
            public void requestVersion(Context context) {
                mPresenter.getContract().requestVersion(context);
            }

            @Override
            public void responseVersion(Map<String,String> map) {
                //dialogUtils.AlertTitle(map.get("deviceID"),"success");
                appVersionText.setText(map.get("versionName"));
            }

            @Override
            public void requestPermission(Context context) {
                mPresenter.getContract().requestPermission(Login.this);
            }

            @Override
            public void requestToken() {
                mPresenter.getContract().requestToken();
            }
        };
    }

    /**【创建实例化一个Presenter】**/
    @Override
    public LoginActivityPresenter createPresenter() {
        return new LoginActivityPresenter();
    }

    /**【拿到、引用布局文件】**/
    @Override
    public int getViewID() {
        return R.layout.activity_login;

    }

    /**【初始化UI组件】**/
    @Override
    public void initViewUI() {
        application.addActivity(Login.this);
        appVersionText = findViewById(R.id.appVersionText);
        userName = findViewById(R.id.EditTextUserName);
        passWord = findViewById(R.id.EditTextPassWord);
        btnLogin = findViewById(R.id.login);
        btnExit = findViewById(R.id.exit);
        btnSetting = findViewById(R.id.setting);
        dialogUtils = new AlertDialogUtils(this);
        alert = dialogUtils.getAlertDialog("warning");
        alert.setCancelable(false);
        preferences = getSharedPreferences("userName",0);
        editor = preferences.edit();

    }

    /**【初始化监听事件】**/
    @Override
    public void initListener() {
        btnLogin.setOnClickListener(this);
        btnExit.setOnClickListener(this);
        btnSetting.setOnClickListener(this);
    }

    /**【销毁时执行方法】**/
    @Override
    public void destroy() {
        if (dialogUtils != null){
            dialogUtils.dismissDialog();
        }
        if (alert != null){
            alert.dismiss();
        }
        dialogUtils = null;
        alert = null;
    }

    /**【初始化数据】**/
    @Override
    public void initDatas() {
        //System.Net.ServicePointManager.ServerCertificateValidationCallback;/* = delegate { return true; };*/
        userName.setText(preferences.getString("userName",""));
        getContract().requestPermission(Login.this);
        getContract().requestVersion(Login.this);

    }

    /**【报错处理】**/
    @Override
    public void responseError(Object o, Throwable throwable) {
        dialogUtils.AlertTitle(throwable.getMessage(),"error");
    }

    /**【点击执行事件】**/
    @Override
    public void onClick(View view) {
        username = userName.getText().toString().trim();
        password = passWord.getText().toString().trim();
        switch (view.getId()){
            case R.id.login:
                editor.putString("userName",username);
                editor.commit();
                /**【交由契约类处理，契约类交给P层，P层交给M层】**/
                getContract().requestLogin(username,password , this);

                break;
            case R.id.exit:

                //getContract().requestToken();     //请求刷新token
                //getContract().requestExit(alert);   //请求退出软件
                //Intent intent2 = new Intent(Login.this, AJVehicleListAndInitLoginAndRecheckLogin.class);
                //startActivity(intent2);

                dialog = AlertDialogUtils.AlertLoading(Login.this);


                Observable<validateCode> observable = RetrofitManager.getRetrofitManager().getApiService().requestCode();

                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new ObserverManager<validateCode>() {
                    @Override
                    public void onSuccess(validateCode validateCode) {
                        validateCode.dataLog();
                        if ( validateCode.getCode() == 200 ){
                            //dialog.dismiss();
                        }
                    }

                    @Override
                    public void onFail(Throwable throwable) {
                        Log.e("Throwable",throwable.toString());
                        final Timer t = new Timer();
                        t.schedule(new TimerTask() {
                            public void run() {
                                dialog.dismiss();
                                t.cancel();
                            }
                        }, 5000);
                    }

                    @Override
                    public void onFinish() {
                        final Timer t = new Timer();
                        t.schedule(new TimerTask() {
                            public void run() {
                                dialog.dismiss();
                                t.cancel();
                            }
                        }, 5000);
                        Log.e("请求验证码结果","完成");
                    }

                    @Override
                    public void onDisposable(Disposable disposable) {
                        dialog.show();
                    }
                });


                break;
            case R.id.setting:
                Intent intent = new Intent(Login.this, Setting.class);
                startActivity(intent);
                break;
        }
    }



    /**【重写返回键】**/
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            getContract().requestExit(alert);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
