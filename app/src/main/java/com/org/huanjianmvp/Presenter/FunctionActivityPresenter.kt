package com.org.huanjianmvp.Presenter

import com.org.huanjianmvp.Activity.Function
import com.org.huanjianmvp.Base.BaseActivityPresenter
import com.org.huanjianmvp.Contrast.FunctionContrast
import com.org.huanjianmvp.Model.FunctionActivityModel

/**
 * Created by Administrator on 2020/8/21.
 */
class FunctionActivityPresenter: BaseActivityPresenter<Function, FunctionActivityModel, FunctionContrast.ViewAndPresenter>(){

    override fun createModel(): FunctionActivityModel {
        return FunctionActivityModel(this)
    }

    override fun getContract(): FunctionContrast.ViewAndPresenter {
        return object : FunctionContrast.ViewAndPresenter{

        }
    }


}