package com.org.huanjianmvp.Activity

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.org.huanjianmvp.Adapter.AJBaseInfoAdapter
import com.org.huanjianmvp.Base.BaseFragment
import com.org.huanjianmvp.Contrast.AJBaseInfoContrast
import com.org.huanjianmvp.Presenter.AJBaseInfoPresenter
import com.org.huanjianmvp.R
import com.org.huanjianmvp.Utils.StaticValues
import kotlinx.android.synthetic.main.aj_base_info.*

/**
 * 安检基本信息  页面
 * */

class AJBaseInfo : BaseFragment<AJBaseInfoPresenter,AJBaseInfoContrast.ViewAndPresenter>() {

    var adapter:  AJBaseInfoAdapter? = null

    override fun getContract(): AJBaseInfoContrast.ViewAndPresenter {
        return object : AJBaseInfoContrast.ViewAndPresenter{

        }
    }

    override fun createPresenter(): AJBaseInfoPresenter {
        return AJBaseInfoPresenter()
    }

    override fun getViewID(): Int {
        return R.layout.aj_base_info
    }

    override fun initViewUI() {

        var list = ArrayList<String>()
        for (i in StaticValues.AJBaseInfoText.indices){
            list.add(StaticValues.AJBaseInfoText[i])
        }

        adapter = AJBaseInfoAdapter(list)

        AJBaseInfoView.layoutManager = LinearLayoutManager(this.context)
        AJBaseInfoView.adapter = adapter
    }

    override fun initListener() {

    }

    override fun onClick(p0: View?) {

    }

    override fun destroy() {

    }

    override fun initDatas() {

    }

    override fun <ERROR : Any?> responseError(error: ERROR, throwable: Throwable?) {

    }

}
