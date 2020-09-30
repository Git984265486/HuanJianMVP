package com.org.huanjianmvp.Utils;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间处理工具类
 * Created by Administrator on 2020/9/3.
 */

public class TimeUtils {


    //获取现在时间
    public static String getNowTime(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = dateFormat.format(new Date());
        return dateStr;
    }


    //获取现在日期
    public static String getNowDay(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = dateFormat.format(new Date());
        return dateStr;
    }

    //时间转时间戳、计算时间差(单位：秒)
    public static int secondDiff(String inputTime)throws Exception{
        long nowTime = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long inputT = dateFormat.parse(inputTime).getTime();
        int time = (int)(nowTime - inputT)/1000;
        Log.e("时间相差", time + "\t秒");
        return time;
    }

    //时间转时间戳、计算时间差(单位：天)
    public static int dayDiff(String inputTime)throws Exception{
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = dateFormat.format(new Date());
        long nowT = dateFormat.parse(dateStr).getTime();
        long inputT = dateFormat.parse(inputTime).getTime();
        int time = (int)(nowT - inputT)/1000/3600/24;
        return time;
    }


}
