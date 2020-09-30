package com.org.huanjianmvp.Model

import com.org.huanjianmvp.Base.BaseActivityModel
import com.org.huanjianmvp.Contrast.AJVehicleListInitLoginRecheckLoginContrast
import com.org.huanjianmvp.Presenter.AJVehicleListInitLoginRecheckLoginPresenter

/**
 * Created by Administrator on 2020/9/16.
 */
class AJVehicleListInitLoginRecheckLoginModel(mPresenter: AJVehicleListInitLoginRecheckLoginPresenter): BaseActivityModel<AJVehicleListInitLoginRecheckLoginPresenter,
        AJVehicleListInitLoginRecheckLoginContrast.Model>(mPresenter) {
    override fun getContract(): AJVehicleListInitLoginRecheckLoginContrast.Model {
        return object : AJVehicleListInitLoginRecheckLoginContrast.Model{

        }
    }
}