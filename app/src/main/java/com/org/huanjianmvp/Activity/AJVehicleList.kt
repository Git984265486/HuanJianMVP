package com.org.huanjianmvp.Activity

import android.view.View
import com.org.huanjianmvp.Base.BaseFragment
import com.org.huanjianmvp.Contrast.AJVehicleListContrast
import com.org.huanjianmvp.Presenter.AJVehicleListPresenter
import com.org.huanjianmvp.R

/**
 * 【安检车辆列表】
 * **/

class AJVehicleList : BaseFragment<AJVehicleListPresenter,AJVehicleListContrast.ViewAndPresenter>() {

    override fun getContract(): AJVehicleListContrast.ViewAndPresenter {
        return object : AJVehicleListContrast.ViewAndPresenter{

        }
    }

    override fun createPresenter(): AJVehicleListPresenter {
        return AJVehicleListPresenter()
    }

    override fun getViewID(): Int {
        return R.layout.aj_vehicle_list
    }

    override fun initViewUI() {

    }

    override fun initListener() {

    }

    override fun onClick(view: View?) {

    }

    override fun destroy() {

    }

    override fun initDatas() {

    }

    override fun <ERROR : Any?> responseError(error: ERROR, throwable: Throwable?) {

    }


}
