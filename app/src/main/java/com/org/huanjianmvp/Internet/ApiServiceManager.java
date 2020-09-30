package com.org.huanjianmvp.Internet;

import com.org.huanjianmvp.Domain.token;
import com.org.huanjianmvp.Domain.validateCode;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * 接口声明、统一管理接口
 * Created by Administrator on 2020/8/20.
 */

public interface ApiServiceManager {

    /**【刷新token请求】**/
    @POST("oauth/token")
    @FormUrlEncoded
    Observable<token> requestToken(@Field("grant_type") String grant_type,@Field("client_id") String client_id,
                                   @Field("client_secret") String client_secret,@Field("refresh_token") String refresh_token);

    /**【请求验证码】**/
    @GET("code/image")
    Observable<validateCode> requestCode();

}
