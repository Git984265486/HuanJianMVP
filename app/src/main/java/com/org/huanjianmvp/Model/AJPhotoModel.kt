package com.org.huanjianmvp.Model

import com.org.huanjianmvp.Base.BaseFragmentModel
import com.org.huanjianmvp.Contrast.AJPhotoContrast
import com.org.huanjianmvp.Presenter.AJPhotoPresenter

/**
 * Created by Administrator on 2020/9/4.
 */
class AJPhotoModel(mPresenter: AJPhotoPresenter): BaseFragmentModel<AJPhotoPresenter,AJPhotoContrast.Model>(mPresenter) {
    override fun getContract(): AJPhotoContrast.Model {
        return object : AJPhotoContrast.Model{

        }
    }
}