package com.org.huanjianmvp.Model

import com.org.huanjianmvp.Base.BaseActivityModel
import com.org.huanjianmvp.Contrast.ChassisContrast
import com.org.huanjianmvp.Presenter.ChassisPresenter

/**
 * Created by Administrator on 2020/9/22.
 */
class ChassisModel(mPresenter: ChassisPresenter): BaseActivityModel<ChassisPresenter , ChassisContrast.Model>(mPresenter) {
    override fun getContract(): ChassisContrast.Model {
        return object : ChassisContrast.Model{

        }
    }
}