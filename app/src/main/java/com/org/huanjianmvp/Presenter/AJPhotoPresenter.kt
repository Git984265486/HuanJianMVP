package com.org.huanjianmvp.Presenter

import com.org.huanjianmvp.Activity.AJPhoto
import com.org.huanjianmvp.Base.BaseFragmentPresenter
import com.org.huanjianmvp.Contrast.AJCheckContrast
import com.org.huanjianmvp.Contrast.AJPhotoContrast
import com.org.huanjianmvp.Model.AJPhotoModel

/**
 * Created by Administrator on 2020/9/4.
 */
class AJPhotoPresenter : BaseFragmentPresenter<AJPhoto,AJPhotoModel,AJPhotoContrast.ViewAndPresenter>() {
    override fun getContract(): AJPhotoContrast.ViewAndPresenter {
        return object : AJPhotoContrast.ViewAndPresenter {

        }
    }

    override fun createModel(): AJPhotoModel {
        return AJPhotoModel(this)
    }
}