package com.org.huanjianmvp.Activity

import android.graphics.Color
import android.support.v4.app.Fragment
import android.view.View
import com.org.huanjianmvp.Base.BaseActivity
import com.org.huanjianmvp.Contrast.AJVehicleListInitLoginRecheckLoginContrast
import com.org.huanjianmvp.Domain.UserData
import com.org.huanjianmvp.Presenter.AJVehicleListInitLoginRecheckLoginPresenter
import com.org.huanjianmvp.R
import kotlinx.android.synthetic.main.aj_vehiclelist__initlogin_rechecklogin.*

/**
 * 安检的 【车辆列表、初检登录、复检登录】 导航页
 * **/

class AJVehicleListAndInitLoginAndRecheckLogin : BaseActivity<AJVehicleListInitLoginRecheckLoginPresenter,
        AJVehicleListInitLoginRecheckLoginContrast.ViewAndPresenter>() {

    var user : UserData? = null
    private var tabText = arrayOf("车辆列表", "初检登录", "复检登录")
    private var fragments = arrayListOf<Fragment>()

    override fun getContract(): AJVehicleListInitLoginRecheckLoginContrast.ViewAndPresenter {
        return object : AJVehicleListInitLoginRecheckLoginContrast.ViewAndPresenter{

        }
    }

    override fun createPresenter(): AJVehicleListInitLoginRecheckLoginPresenter {
        return AJVehicleListInitLoginRecheckLoginPresenter()
    }

    override fun getViewID(): Int {
        return R.layout.aj_vehiclelist__initlogin_rechecklogin
    }

    override fun initViewUI() {
        application.addActivity(this@AJVehicleListAndInitLoginAndRecheckLogin)      //添加页面到统一管理中

        fragments.add(AJVehicleList())
        fragments.add(AJInitLogin())
        fragments.add(AJRecheckLogin())

        AJLoginNavigationBar.defaultSetting()
        AJLoginNavigationBar.titleItems(tabText)                                               //tab文字集合
        AJLoginNavigationBar.fragmentList(fragments)                                           //Fragment集合
        AJLoginNavigationBar.tabTextSize(18)                                      //tab文字大小
        AJLoginNavigationBar.navigationHeight(40)                            //导航栏高度
        AJLoginNavigationBar.normalTextColor(Color.parseColor("#ffffff"))          //字体未选中的颜色
        AJLoginNavigationBar.selectTextColor(Color.parseColor("#FF0000"))          //字体选中的颜色
        AJLoginNavigationBar.navigationBackground(Color.parseColor("#5bc0de"))     //导航栏背景色   3F9AE2
        AJLoginNavigationBar.fragmentManager(supportFragmentManager)
        AJLoginNavigationBar.build()
    }

    override fun initListener() {

    }

    override fun onClick(view: View?) {

    }

    override fun destroy() {

    }

    override fun initDatas() {
        user = intent.getSerializableExtra("data") as UserData     //获取传递过来的对象数据
    }

    override fun <ERROR : Any?> responseError(error: ERROR, throwable: Throwable?) {

    }

}
