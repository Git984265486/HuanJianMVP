package com.org.huanjianmvp.Utils;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import java.io.IOException;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * 网络工具类
 * Created by Administrator on 2020/9/25.
 */

public class NetworkUtils {

    /**【判断WiFi是否连接】**/
    public static boolean WiFiIsConnect(Context context){
        ConnectivityManager connectManager = (ConnectivityManager)context.getSystemService(CONNECTIVITY_SERVICE);
        boolean isConnect = connectManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected();     //判断wifi是否连接
        return isConnect;
    }

    /**【获取WiFi信号强度】**/
    public static void WiFiIntensity(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (info.isConnected()){                //WiFi是否连接
            WifiManager wifi = (WifiManager)context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = wifi.getConnectionInfo();
            int intensity = wifiInfo.getRssi(); //WiFi信号强度
            if (intensity >= -50 && intensity < 0){
                Log.e("当前WiFi信号强度","\t\t" + intensity);
            }else if (intensity >= -70 && intensity < -50){
                Log.e("当前WiFi信号强度","\t\t" + intensity);
            }else if (intensity >= -80 && intensity < -70){
                Log.e("当前WiFi信号强度","\t\t" + intensity);
            }else if (intensity >= -100 && intensity < -80){
                Log.e("当前WiFi信号强度","\t\t" + intensity);
            }
        }
    }

    /**【判断WiFi网络是否可用、IP地址是否可以ping通】**/
    public static boolean WiFiIsConnectedNetwork(Context context) throws Exception {
        ConnectivityManager manager = (ConnectivityManager)context.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo network = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        boolean result = false;
        if (network.isConnected()){
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec("ping -c 3 127.0.0.1");
            result = (process.waitFor()==0);
            Log.e("ping百度地址结果","\t\t" + process.waitFor() + "\t\t" + result);
        }
        return result;
    }
}
