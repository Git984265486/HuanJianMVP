package com.org.huanjianmvp.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 继承自Fragment的基类
 * Created by Administrator on 2020/8/24.
 */

public abstract class BaseFragment<P extends BaseFragmentPresenter , CONTRACT>
        extends Fragment implements View.OnClickListener{

    public P mPresenter;

    public abstract CONTRACT getContract();     //方法接口，实现接口中的方法

    public abstract P createPresenter();        //实例化P层

    public abstract int getViewID();            //拿到布局视图

    public abstract void initViewUI();          //初始化组件

    public abstract void initListener();        //初始化监听

    public abstract void destroy();             //销毁时执行方法

    public abstract void initDatas();           //初始化数据

    //处理相应错误信息
    public abstract<ERROR extends Object> void responseError(ERROR error , Throwable throwable);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mPresenter = createPresenter();        //实例化P层
        mPresenter.attach(this);              //绑定
        return inflater.inflate(getViewID(),null);
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initDatas();                                //初始化数据
        initViewUI();                               //初始化UI组件
        initListener();                             //初始化监听
    }



    @Override
    public void onDestroyView() {
        destroy();                                  //销毁时触发
        mPresenter.detach();                        //解绑
        super.onDestroyView();
    }
}
