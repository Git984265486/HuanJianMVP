package com.org.huanjianmvp.Internet;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Observer封装、可在各个方法中做一些封装或数据处理
 * 由子类来实现抽象方法
 * Created by Administrator on 2020/8/20.
 */

public abstract class ObserverManager<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {
        onDisposable(d);
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onComplete() {
        onFinish();
    }

    @Override
    public void onError(Throwable e) {
        onFail(e);
    }

    public abstract void onSuccess(T t);                        //调用成功
    public abstract void onFail(Throwable throwable);           //调用失败或者报错
    public abstract void onFinish();                            //调用完成
    public abstract void onDisposable(Disposable disposable);   //调用前准备工作
}
