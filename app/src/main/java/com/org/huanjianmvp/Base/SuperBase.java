package com.org.huanjianmvp.Base;

/**
 * 整个架构的父类、超级父类
 * 泛型 CONTRACT 面向的是接口类
 * 子类必须实现方法接口getContract()，具体实现哪些看传过来的方法接口中有哪些方法
 * Created by Administrator on 2020/8/19.
 */

public abstract class SuperBase<CONTRACT> {

    /*【子类要实现的方法，具体实现由传过来的接口中的方法决定】*/
    public abstract CONTRACT getContract();

}
