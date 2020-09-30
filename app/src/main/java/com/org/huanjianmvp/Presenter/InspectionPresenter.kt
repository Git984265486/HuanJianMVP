package com.org.huanjianmvp.Presenter

import com.org.huanjianmvp.Activity.Inspection
import com.org.huanjianmvp.Base.BaseFragmentPresenter
import com.org.huanjianmvp.Contrast.InspectionContrast
import com.org.huanjianmvp.Model.InspectionFragmentModel

/**
 * Created by Administrator on 2020/8/24.
 */
class InspectionPresenter : BaseFragmentPresenter<Inspection,InspectionFragmentModel,InspectionContrast.ViewAndPresenter>(){
    override fun getContract(): InspectionContrast.ViewAndPresenter {
        return object : InspectionContrast.ViewAndPresenter{

        }
    }

    override fun createModel(): InspectionFragmentModel {
        return InspectionFragmentModel(this)
    }
}