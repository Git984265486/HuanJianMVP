package com.org.huanjianmvp.Model

import com.org.huanjianmvp.Base.BaseFragmentModel
import com.org.huanjianmvp.Contrast.AJVehicleListContrast
import com.org.huanjianmvp.Presenter.AJVehicleListPresenter

/**
 * Created by Administrator on 2020/9/16.
 */
class AJVehicleListModel(mPresenter: AJVehicleListPresenter) : BaseFragmentModel<AJVehicleListPresenter,AJVehicleListContrast.Model>(mPresenter) {
    override fun getContract(): AJVehicleListContrast.Model {
        return object : AJVehicleListContrast.Model{

        }
    }
}