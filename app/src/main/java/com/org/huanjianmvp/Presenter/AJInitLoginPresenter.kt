package com.org.huanjianmvp.Presenter

import com.org.huanjianmvp.Activity.AJInitLogin
import com.org.huanjianmvp.Base.BaseFragmentPresenter
import com.org.huanjianmvp.Contrast.AJInitLoginContrast
import com.org.huanjianmvp.Model.AJInitLoginModel

/**
 * Created by Administrator on 2020/9/17.
 */
class AJInitLoginPresenter : BaseFragmentPresenter<AJInitLogin , AJInitLoginModel , AJInitLoginContrast.ViewAndPresenter>() {
    override fun getContract(): AJInitLoginContrast.ViewAndPresenter {
        return object : AJInitLoginContrast.ViewAndPresenter{

        }
    }

    override fun createModel(): AJInitLoginModel {
        return AJInitLoginModel(this)
    }
}