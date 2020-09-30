package com.org.huanjianmvp.Model

import com.org.huanjianmvp.Base.BaseActivityModel
import com.org.huanjianmvp.Contrast.AJCheckPhotoBaseContrast
import com.org.huanjianmvp.Presenter.AJCheckPhotoBasePresenter

/**
 * Created by Administrator on 2020/9/4.
 */
class AJCheckPhotoBaseModel(mPresenter: AJCheckPhotoBasePresenter?)
    :BaseActivityModel<AJCheckPhotoBasePresenter,AJCheckPhotoBaseContrast.Model>(mPresenter) {

    override fun getContract(): AJCheckPhotoBaseContrast.Model {
        return object : AJCheckPhotoBaseContrast.Model{

        }
    }
}