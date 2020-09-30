package com.org.huanjianmvp.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.org.huanjianmvp.Domain.UserData;

/**
 * recyclerView组件图片点击事件监听
 * Created by Administrator on 2020/9/1.
 */

public interface PhotoClickListener {

    //参数（父组件，当前单击的View,单击的View的位置，数据）
    void onPhotoClick(RecyclerView parent, View view, int position ,UserData data);

}
