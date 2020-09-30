package com.org.huanjianmvp.Presenter

import com.org.huanjianmvp.Activity.AJRecheckLogin
import com.org.huanjianmvp.Base.BaseFragmentPresenter
import com.org.huanjianmvp.Contrast.AJRecheckLoginContrast
import com.org.huanjianmvp.Model.AJRecheckLoginModel

/**
 * Created by Administrator on 2020/9/17.
 */
class AJRecheckLoginPresenter: BaseFragmentPresenter<AJRecheckLogin,AJRecheckLoginModel,AJRecheckLoginContrast.ViewAndPresenter>() {
    override fun getContract(): AJRecheckLoginContrast.ViewAndPresenter {
        return object : AJRecheckLoginContrast.ViewAndPresenter{

        }
    }

    override fun createModel(): AJRecheckLoginModel {
        return AJRecheckLoginModel(this)
    }
}