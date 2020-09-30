package com.org.huanjianmvp.Domain;

import android.util.Log;

/**
 * Created by Administrator on 2020/9/28.
 */

public class validateCode {

    private int code;
    private String msg;
    private int count;

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

    class data{
        public String imageKey;
        private String imageCode;

        public String getImageKey() {
            return imageKey;
        }

        public void setImageKey(String imageKey) {
            this.imageKey = imageKey;
        }

        public String getImageCode() {
            return imageCode;
        }

        public void setImageCode(String imageCode) {
            this.imageCode = imageCode;
        }
    }

    public void dataLog(){
        Log.e("code",code + "\t\tmsg:" + msg + "\t\tcount:" + count );
    }

}
