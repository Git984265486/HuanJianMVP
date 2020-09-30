package com.org.huanjianmvp.Domain;

import android.util.Log;

/**
 * Created by Administrator on 2020/8/27.
 */

public class token {

    private int code;
    private String msg;
    private int count;
    private String data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void tokenShow(){
        Log.i("code",code+"");
        Log.i("msg",msg+"");
        Log.i("count",count+"");
    }
}
