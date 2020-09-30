package com.org.huanjianmvp.Activity

import android.graphics.Color
import android.support.v4.app.Fragment
import android.view.KeyEvent
import android.view.View
import com.org.huanjianmvp.Base.BaseActivity
import com.org.huanjianmvp.Contrast.AJCheckPhotoBaseContrast
import com.org.huanjianmvp.Presenter.AJCheckPhotoBasePresenter
import com.org.huanjianmvp.R
import kotlinx.android.synthetic.main.aj_check__photo__baseinfo.*

/**
 * 安检的 【外检检验、外检拍照、基本信息】 导航页
 * **/

class AJCheckAndPhotoAndBaseInfo : BaseActivity<AJCheckPhotoBasePresenter,AJCheckPhotoBaseContrast.ViewAndPresenter>() {

    private var tabText = arrayOf("外检检验", "外检拍照", "基本信息")
    private var fragments = arrayListOf<Fragment>()

    override fun getContract(): AJCheckPhotoBaseContrast.ViewAndPresenter {
        return object : AJCheckPhotoBaseContrast.ViewAndPresenter{

        }
    }

    override fun createPresenter(): AJCheckPhotoBasePresenter {
        return AJCheckPhotoBasePresenter()
    }

    override fun getViewID(): Int {
        return R.layout.aj_check__photo__baseinfo
    }

    override fun initViewUI() {
        application.addActivity(this@AJCheckAndPhotoAndBaseInfo)                //添加页面到统一管理中
        fragments.add(AJCheck())
        fragments.add(AJPhoto())
        fragments.add(AJBaseInfo())
        AJnavigationBar.defaultSetting()
        AJnavigationBar.titleItems(tabText)                                               //tab文字集合
        AJnavigationBar.fragmentList(fragments)                                           //Fragment集合
        AJnavigationBar.tabTextSize(18)                                      //tab文字大小
        AJnavigationBar.navigationHeight(40)                            //导航栏高度
        AJnavigationBar.normalTextColor(Color.parseColor("#ffffff"))          //字体未选中的颜色
        AJnavigationBar.selectTextColor(Color.parseColor("#FF0000"))          //字体选中的颜色
        AJnavigationBar.navigationBackground(Color.parseColor("#5bc0de"))     //导航栏背景色
        AJnavigationBar.fragmentManager(supportFragmentManager)
        AJnavigationBar.build()
    }

    override fun initListener() {

    }

    override fun onClick(p0: View?) {

    }

    override fun destroy() {
        this@AJCheckAndPhotoAndBaseInfo.finish()
    }

    override fun initDatas() {

    }

    override fun <ERROR : Any?> responseError(error: ERROR, throwable: Throwable?) {

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        this@AJCheckAndPhotoAndBaseInfo.finish()
        return super.onKeyDown(keyCode, event)
    }

}
