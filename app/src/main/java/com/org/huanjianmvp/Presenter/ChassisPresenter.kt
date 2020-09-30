package com.org.huanjianmvp.Presenter

import com.org.huanjianmvp.Activity.Chassis
import com.org.huanjianmvp.Base.BaseActivityPresenter
import com.org.huanjianmvp.Contrast.ChassisContrast
import com.org.huanjianmvp.Model.ChassisModel

/**
 * Created by Administrator on 2020/9/22.
 */
class ChassisPresenter : BaseActivityPresenter<Chassis , ChassisModel , ChassisContrast.ViewAndPresenter>() {
    override fun getContract(): ChassisContrast.ViewAndPresenter {
        return object : ChassisContrast.ViewAndPresenter{

        }
    }

    override fun createModel(): ChassisModel {
        return ChassisModel(this)
    }
}