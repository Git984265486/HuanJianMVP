package com.org.huanjianmvp.Presenter

import com.org.huanjianmvp.Activity.InspectionAndPhotograph
import com.org.huanjianmvp.Base.BaseActivityPresenter
import com.org.huanjianmvp.Contrast.InspectAndPhotoContrast
import com.org.huanjianmvp.Model.InspectAndPhotoActivityModel

/**
 * Created by Administrator on 2020/8/24.
 */

class InspectAndPhotoPresenter: BaseActivityPresenter<InspectionAndPhotograph,InspectAndPhotoActivityModel,InspectAndPhotoContrast.ViewAndModel>(){
    override fun getContract(): InspectAndPhotoContrast.ViewAndModel {
        return object : InspectAndPhotoContrast.ViewAndModel{

        }
    }

    override fun createModel(): InspectAndPhotoActivityModel {
        return InspectAndPhotoActivityModel(this)
    }

}
