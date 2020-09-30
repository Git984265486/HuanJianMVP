package com.org.huanjianmvp;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509ExtendedTrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * 用来管理所有的activity，当我们退出的时候调用集合依次finish
 * Created by Administrator on 2020/8/31.
 */

public class BootApplication extends Application {

    private ArrayList<Activity> listActivity;

    @Override
    public void onCreate() {
        super.onCreate();
        listActivity = new ArrayList<>();
    }

    /**【添加启动的activity到数组中】**/
    public void addActivity(Activity activity){
        listActivity.add(activity);
    }

    /**【关闭数组中的每个activity，释放内存】**/
    public void destroyActivity(){
        try{
            for (Activity activity : listActivity){
                if (activity != null){
                    activity.finish();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
