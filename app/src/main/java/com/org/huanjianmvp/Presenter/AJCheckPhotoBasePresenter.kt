package com.org.huanjianmvp.Presenter

import com.org.huanjianmvp.Activity.AJCheckAndPhotoAndBaseInfo
import com.org.huanjianmvp.Base.BaseActivityPresenter
import com.org.huanjianmvp.Contrast.AJCheckPhotoBaseContrast
import com.org.huanjianmvp.Model.AJCheckPhotoBaseModel

/**
 * Created by Administrator on 2020/9/4.
 */
class AJCheckPhotoBasePresenter : BaseActivityPresenter<AJCheckAndPhotoAndBaseInfo,AJCheckPhotoBaseModel,AJCheckPhotoBaseContrast.ViewAndPresenter>() {
    override fun getContract(): AJCheckPhotoBaseContrast.ViewAndPresenter {
        return object : AJCheckPhotoBaseContrast.ViewAndPresenter{

        }
    }

    override fun createModel(): AJCheckPhotoBaseModel {
        return AJCheckPhotoBaseModel(this)
    }
}