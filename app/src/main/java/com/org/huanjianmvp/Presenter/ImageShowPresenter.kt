package com.org.huanjianmvp.Presenter

import com.org.huanjianmvp.Activity.ImageShow
import com.org.huanjianmvp.Base.BaseActivityPresenter
import com.org.huanjianmvp.Contrast.ImageShowContrast
import com.org.huanjianmvp.Model.ImageShowModel

/**
 * Created by Administrator on 2020/9/11.
 */
class ImageShowPresenter: BaseActivityPresenter<ImageShow,ImageShowModel,ImageShowContrast.ViewAndPresenter>() {
    override fun getContract(): ImageShowContrast.ViewAndPresenter {
        return object : ImageShowContrast.ViewAndPresenter{

        }
    }

    override fun createModel(): ImageShowModel {
        return ImageShowModel(this)
    }
}