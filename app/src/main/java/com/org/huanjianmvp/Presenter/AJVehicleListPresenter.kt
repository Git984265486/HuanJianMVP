package com.org.huanjianmvp.Presenter

import com.org.huanjianmvp.Activity.AJVehicleList
import com.org.huanjianmvp.Base.BaseFragmentPresenter
import com.org.huanjianmvp.Contrast.AJVehicleListContrast
import com.org.huanjianmvp.Model.AJVehicleListModel

/**
 * Created by Administrator on 2020/9/16.
 */
class AJVehicleListPresenter: BaseFragmentPresenter<AJVehicleList , AJVehicleListModel ,AJVehicleListContrast.ViewAndPresenter>() {
    override fun getContract(): AJVehicleListContrast.ViewAndPresenter {
        return object : AJVehicleListContrast.ViewAndPresenter{

        }
    }

    override fun createModel(): AJVehicleListModel {
        return AJVehicleListModel(this)
    }
}