package com.org.huanjianmvp.Presenter

import com.org.huanjianmvp.Activity.AJBaseInfo
import com.org.huanjianmvp.Base.BaseFragmentPresenter
import com.org.huanjianmvp.Contrast.AJBaseInfoContrast
import com.org.huanjianmvp.Model.AJBaseInfoModel

/**
 * Created by Administrator on 2020/9/4.
 */
class AJBaseInfoPresenter : BaseFragmentPresenter<AJBaseInfo,AJBaseInfoModel,AJBaseInfoContrast.ViewAndPresenter>(){
    override fun getContract(): AJBaseInfoContrast.ViewAndPresenter {
        return object : AJBaseInfoContrast.ViewAndPresenter{

        }
    }

    override fun createModel(): AJBaseInfoModel {
        return AJBaseInfoModel(this)
    }
}