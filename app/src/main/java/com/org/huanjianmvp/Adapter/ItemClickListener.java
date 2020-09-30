package com.org.huanjianmvp.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.org.huanjianmvp.Domain.UserData;

/**
 * 登录后的列表监听，给RecyclerView子项实现点击监听事件
 * Created by Administrator on 2020/8/19.
 */

public interface ItemClickListener {

    //参数（parent父组件 ， view当前单击的View , position单击的View的位置 ， data返回的数据）
    void onItemClick(RecyclerView parent, View view, int position, UserData data);

}
