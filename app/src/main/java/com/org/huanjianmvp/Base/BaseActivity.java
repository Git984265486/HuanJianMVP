package com.org.huanjianmvp.Base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.org.huanjianmvp.BootApplication;
import com.org.huanjianmvp.Login;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 只关注P层，不与V层进行交互
 * 继承自AppCompatActivity的基类
 * Created by Administrator on 2020/8/19.
 */

public abstract class BaseActivity<P extends BaseActivityPresenter, CONTRACT>
        extends AppCompatActivity implements View.OnClickListener{

    public P mPresenter;

    private Long startTime = System.currentTimeMillis();    //第一次点击时间
    private Long clickTime = System.currentTimeMillis();    //每一次点击都重置该时间，判断两次时间间隔
    private Intent intent ;                                 //超时跳转页面
    private SweetAlertDialog alertDialog;                   //超时提示框
    public BootApplication application;                     //程序页面管理

    public abstract CONTRACT getContract();     //方法接口，实现接口中的方法

    public abstract P createPresenter();        //实例化P层

    public abstract int getViewID();            //拿到布局视图

    public abstract void initViewUI();          //初始化组件

    public abstract void initListener();        //初始化监听

    public abstract void destroy();             //销毁时执行方法

    public abstract void initDatas();           //初始化数据

    //处理相应错误信息
    public abstract<ERROR extends Object> void responseError(ERROR error , Throwable throwable);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(getViewID());                //引入布局文件
        this.mPresenter = createPresenter();        //实例化P层
        application = (BootApplication)getApplication();
        mPresenter.attach(this);              //绑定
        initViewUI();                               //初始化UI组件
        initListener();                             //初始化监听
        initDatas();                                //初始化数据
    }

    /**
     * 【登录长时间无操作】
     * 【每点击屏幕就触发该事件】**/
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN :          //屏幕按下
                clickTime = System.currentTimeMillis();
                if (clickTime - startTime > 1000 * 60 * 10){    //屏幕十分钟无操作
                    intent = new Intent(this, Login.class);
                    if (alertDialog == null){
                        alertDialog = new SweetAlertDialog(this,SweetAlertDialog.WARNING_TYPE);
                    }
                    //Log.e("点击时间",clickTime + "\t\t开始时间\t\t" + startTime + "\t\t相差时间\t\t" + (clickTime - startTime));
                    alertDialog.setCancelable(false);
                    alertDialog.setTitleText("登录超时！");
                    alertDialog.setContentText("程序十分钟无操作，返回重新登录！");
                    alertDialog.setConfirmText("确定");
                    alertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            clickTime = System.currentTimeMillis();
                            startTime = clickTime;
                            startActivity(intent);
                            application.destroyActivity();
                        }
                    });
                    alertDialog.show();
                    return true;        //返回值为True事件会传递到自己的onTouchEvent()  返回值为False传递到下一个view的dispatchTouchEvent()
                }else {
                    startTime = clickTime;
                    //Log.e("时间clickTime",clickTime + "\t\t时间startTime\t\t" + startTime + "\t\t相差时间\t\t" + (clickTime - startTime));
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onDestroy() {
        destroy();                                  //销毁时触发
        mPresenter.detach();                        //解绑
        if (alertDialog!=null){
            alertDialog.dismiss();
        }
        super.onDestroy();
    }

}
