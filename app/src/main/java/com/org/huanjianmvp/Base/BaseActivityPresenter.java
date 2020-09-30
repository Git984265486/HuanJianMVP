package com.org.huanjianmvp.Base;

import java.lang.ref.WeakReference;

/**
 * 拿到V层和M层，View必须是继承BaseActivity
 * Presenter关联M和V，M层和V层不能有交互，通过中间层P来进行交互
 * Created by Administrator on 2020/8/19.
 */

public abstract class BaseActivityPresenter<V extends BaseActivity , M extends BaseActivityModel, CONTRACT> extends SuperBase<CONTRACT>  {

    public WeakReference<V> weakView;  //弱化引用
    public M mModel;

    public abstract M createModel();        //创建一个model，具体model由子类传入决定

    //实例化model，建立与M得关系
    public BaseActivityPresenter(){
        mModel = createModel();
    }


    //建立与V的关系，绑定
    public void attach(V view){
        weakView = new WeakReference<>(view);
    }

    //解绑
    public void detach(){
        if (weakView.get()!=null){
            weakView.get().finish();
        }
        if (weakView != null){
            weakView.clear();
            weakView=null;
        }


    }

}
