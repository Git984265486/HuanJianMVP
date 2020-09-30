package com.org.huanjianmvp.Domain;

import android.util.Log;

import java.io.Serializable;

/**
 * 一个用户实体类
 * Created by Administrator on 2020/8/19.
 */

public class UserData implements Serializable {

    private String userName;
    private String age;
    private String sex;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void ShowLog(){
        Log.e("姓名userName",getUserName() + "");
        Log.e("年龄age",getAge()+ "");
        Log.e("性别sex",getSex()+ "");
    }
}
