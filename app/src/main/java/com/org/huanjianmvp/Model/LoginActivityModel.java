package com.org.huanjianmvp.Model;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import com.org.huanjianmvp.Base.BaseActivityModel;
import com.org.huanjianmvp.Contrast.LoginContrast;
import com.org.huanjianmvp.Domain.token;
import com.org.huanjianmvp.Internet.ObserverManager;
import com.org.huanjianmvp.Internet.RetrofitManager;
import com.org.huanjianmvp.Presenter.LoginActivityPresenter;
import com.org.huanjianmvp.Utils.AlertDialogUtils;
import com.org.huanjianmvp.Utils.NetworkUtils;
import com.org.huanjianmvp.Utils.TimeUtils;

import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * 在这里实现数据处理
 * Created by Administrator on 2020/8/19.
 */

public class LoginActivityModel extends BaseActivityModel<LoginActivityPresenter,LoginContrast.Model> {

    public LoginActivityModel(LoginActivityPresenter mPresenter) {
        super(mPresenter);
    }

    @Override
    public LoginContrast.Model getContract() {
        return new LoginContrast.Model() {

            /**【登录验证】**/
            @Override
            public void validateLogin(String userName, String passWord , Context context) throws Exception {
                int time = TimeUtils.secondDiff(TimeUtils.getNowTime());            //服务器时间与本地时间差
                String loginMsg ;                                                   //登录返回结果
                if (NetworkUtils.WiFiIsConnect(context)){                           //手机是否连接WiFi
                    NetworkUtils.WiFiIntensity(context);                            //WiFi信号强度
                    if (time >= -15 && time <= 15){                                 //本地时间与服务器时间相差是否超过15秒
                        if (!userName.equals("") && userName.length() != 0){        //判断用户名不为空
                            if (!passWord.equals("") && passWord.length() != 0 ){   //判断用户密码不为空
                                if (userName.equals("admin")){
                                    loginMsg = "登录成功";
                                }else{
                                    loginMsg = "登录失败";
                                }
                            }else{
                                loginMsg = "登录密码不能为空！";
                            }
                        }else{
                            loginMsg = "用户名不能为空！";
                        }
                    }else{
                        loginMsg = "本地机与服务器时间相差不能超过15秒！";
                    }
                }else{
                    loginMsg = "WiFi未连接，请先连接WiFi";
                }
                presenter.getContract().responseResult(loginMsg);
            }

            /**【请求退出程序】**/
            @Override
            public void exitAction(final SweetAlertDialog alert) {
                if (alert != null){
                    alert.setTitle("是否退出当前软件?");
                    alert.setConfirmText("确定");
                    alert.setCancelText("取消");
                    alert.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            presenter.getContract().responseExit(true);
                        }
                    });
                    alert.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            alert.dismiss();
                        }
                    });
                    alert.show();
                }
            }

            /**【获取程序版本号、手机识别标识】**/
            @Override
            public void versionAction(Context context) {
                Map<String,String> map = new HashMap<>();
                try {
                    /**【获取程序版本】**/
                    PackageManager manager = context.getPackageManager();
                    PackageInfo info = manager.getPackageInfo(context.getPackageName(),0);
                    String versionName = info.versionName;
                    if (versionName != null){
                        versionName = "版本号：20.08.28.20(v" + versionName + ")";
                    }else {
                        versionName = "版本号：20.08.28.20(v3.3.3)";
                    }
                    map.put("versionName",versionName);

                    /**【获取设备识别标识】**/
                    TelephonyManager telephony = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
                    @SuppressLint("MissingPermission")
                    String deviceID = telephony.getDeviceId();
                    if (TextUtils.isEmpty(deviceID)){
                        deviceID = Settings.System.getString(context.getContentResolver(),Settings.Secure.ANDROID_ID);
                    }
                    map.put("deviceID",deviceID);

                    presenter.getContract().responseVersion(map);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }

            }

            /**【权限申请】**/
            @Override
            public void permissionAction(Context context) {
                String [] permissions = new String[] {
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA,
                    Manifest.permission.RECORD_AUDIO,
                };
                ActivityCompat.requestPermissions((Activity) context,permissions,100);
            }

            /**【刷新token请求】**/
            @Override
            public void tokenAction() {
                Observable<token> observable = RetrofitManager.getRetrofitManager().getApiService()
                        .requestToken("refresh_token","web_client","web_secret","7f30f196-d063-4678-afd8-a31644620d03");

                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new ObserverManager<token>() {
                            @Override
                            public void onSuccess(token token) {
                                token.tokenShow();
                            }

                            @Override
                            public void onFail(Throwable throwable) {
                                Log.e("错误信息",throwable.toString());
                            }

                            @Override
                            public void onFinish() {
                                Log.e("请求信息","请求完成！");
                            }

                            @Override
                            public void onDisposable(Disposable disposable) {

                            }
                        });
            }
        };
    }
}
