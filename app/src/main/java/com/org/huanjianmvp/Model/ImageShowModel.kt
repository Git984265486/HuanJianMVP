package com.org.huanjianmvp.Model

import com.org.huanjianmvp.Base.BaseActivityModel
import com.org.huanjianmvp.Contrast.ImageShowContrast
import com.org.huanjianmvp.Presenter.ImageShowPresenter

/**
 * Created by Administrator on 2020/9/11.
 */
class ImageShowModel(mPresenter: ImageShowPresenter?): BaseActivityModel<ImageShowPresenter,ImageShowContrast.Model>(mPresenter) {
    override fun getContract(): ImageShowContrast.Model {
        return object : ImageShowContrast.Model{

        }
    }
}