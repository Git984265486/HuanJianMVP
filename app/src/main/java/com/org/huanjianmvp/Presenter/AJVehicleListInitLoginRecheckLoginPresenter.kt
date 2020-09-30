package com.org.huanjianmvp.Presenter

import com.org.huanjianmvp.Activity.AJVehicleListAndInitLoginAndRecheckLogin
import com.org.huanjianmvp.Base.BaseActivityPresenter
import com.org.huanjianmvp.Contrast.AJVehicleListInitLoginRecheckLoginContrast
import com.org.huanjianmvp.Model.AJVehicleListInitLoginRecheckLoginModel

/**
 * Created by Administrator on 2020/9/16.
 */
class AJVehicleListInitLoginRecheckLoginPresenter: BaseActivityPresenter<AJVehicleListAndInitLoginAndRecheckLogin,AJVehicleListInitLoginRecheckLoginModel,
        AJVehicleListInitLoginRecheckLoginContrast.ViewAndPresenter>() {
    override fun getContract(): AJVehicleListInitLoginRecheckLoginContrast.ViewAndPresenter {
        return object : AJVehicleListInitLoginRecheckLoginContrast.ViewAndPresenter{

        }
    }

    override fun createModel(): AJVehicleListInitLoginRecheckLoginModel {
        return AJVehicleListInitLoginRecheckLoginModel(this)
    }
}