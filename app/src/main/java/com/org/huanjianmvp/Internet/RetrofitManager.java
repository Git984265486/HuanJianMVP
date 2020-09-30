package com.org.huanjianmvp.Internet;

import javax.net.ssl.SSLContext;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit管理器，保证Retrofit在类中只有一个实例，避免请求体的多次创建
 * 封装Retrofit
 * Created by Administrator on 2020/8/20.
 */

public class RetrofitManager {

    private volatile static RetrofitManager retrofitManager;
    private Retrofit retrofit;

    //没有参数的单例模式
    public static RetrofitManager getRetrofitManager(){
        if (retrofitManager == null){
            synchronized (RetrofitManager.class){
                retrofitManager = new RetrofitManager();
            }
        }
        return retrofitManager;
    }

    //没有参数的构造方法
    public RetrofitManager(){
        initRetrofitManager();
    }

    //构造方法创建Retrofit实例
    private void initRetrofitManager(){
        // 09.29    跳过https校验客户端配置
        SSLContext sslContext = IgnoreHttpsValidate.getSSLContext();
        OkHttpClient client = new OkHttpClient.Builder()
                .hostnameVerifier(IgnoreHttpsValidate.doNotVerifier)
                .sslSocketFactory(sslContext.getSocketFactory())    //得到sslSocketFactory实例   设置sllsocketfactory
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://47.97.178.108:8090/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())  //添加Rx转换器
                .addConverterFactory(GsonConverterFactory.create())         //添加Gson转换器
                .client(client)     // 09/29，设置客户端的请求
                .build();
    }

    //获取网络接口实例
    public ApiServiceManager getApiService(){
        return retrofit.create(ApiServiceManager.class);
    }


}
