package com.org.huanjianmvp.Base;

/**
 * 拿到P层，把结果给P层
 * 将由子类传泛型 CONTRACT 到父类中
 * 子类传入到父类中的泛型 CONTRACT 一般为方法接口，具体子类要实现的方法由传入的方法接口决定
 * Created by Administrator on 2020/8/19.
 */

public abstract class BaseFragmentModel<P extends BaseFragmentPresenter, CONTRACT> extends SuperBase<CONTRACT> {

    public P presenter;

    public BaseFragmentModel(P mPresenter){
        this.presenter = mPresenter;    //拿到P层
    }

}
