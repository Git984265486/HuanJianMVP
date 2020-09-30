package com.org.huanjianmvp.Activity

import android.graphics.Color
import android.support.v4.app.Fragment
import android.util.Log
import android.view.KeyEvent
import android.view.View
import com.org.huanjianmvp.Base.BaseActivity
import com.org.huanjianmvp.Contrast.InspectAndPhotoContrast
import com.org.huanjianmvp.Domain.UserData
import com.org.huanjianmvp.Presenter.InspectAndPhotoPresenter
import com.org.huanjianmvp.R
import kotlinx.android.synthetic.main.inspection_and_photograph.*

/**
 * 【外检检验】 和 【外检拍照】 导航页
 **/
class InspectionAndPhotograph : BaseActivity<InspectAndPhotoPresenter,InspectAndPhotoContrast.ViewAndModel>() {

    var user : UserData? = null
    private var tabText = arrayOf("外检检验", "外检拍照")
    private var fragments = arrayListOf<Fragment>()

    override fun getContract(): InspectAndPhotoContrast.ViewAndModel {
        return object : InspectAndPhotoContrast.ViewAndModel{

        }
    }

    override fun createPresenter(): InspectAndPhotoPresenter {
        return InspectAndPhotoPresenter()
    }

    override fun getViewID(): Int {
        return R.layout.inspection_and_photograph
    }

    override fun initViewUI() {
        application.addActivity(this@InspectionAndPhotograph)
        fragments.add(Inspection())
        fragments.add(Photograph())
        navigationBar.defaultSetting()
        navigationBar.titleItems(tabText)                                               //tab文字集合
        navigationBar.fragmentList(fragments)                                           //Fragment集合
        navigationBar.tabTextSize(18)                                      //tab文字大小
        navigationBar.navigationHeight(40)                            //导航栏高度
        navigationBar.normalTextColor(Color.parseColor("#ffffff"))          //字体未选中的颜色
        navigationBar.selectTextColor(Color.parseColor("#FF0000"))          //字体选中的颜色
        navigationBar.navigationBackground(Color.parseColor("#5bc0de"))     //导航栏背景色
        navigationBar.fragmentManager(supportFragmentManager)
        navigationBar.build()
    }

    override fun initListener() {

    }

    override fun onClick(view: View?) {

    }

    override fun destroy() {
        this@InspectionAndPhotograph.finish()
    }

    override fun initDatas() {
        user = intent.getSerializableExtra("data") as UserData
        user?.ShowLog()
        Log.e("外检检验拍照页面打印数据","【完毕】")
    }

    override fun <ERROR : Any?> responseError(error: ERROR, throwable: Throwable?) {

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        this@InspectionAndPhotograph.finish()
        return super.onKeyDown(keyCode, event)
    }

}
