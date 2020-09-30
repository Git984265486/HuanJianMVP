package com.org.huanjianmvp.Model

import com.org.huanjianmvp.Base.BaseActivityModel
import com.org.huanjianmvp.Contrast.InspectAndPhotoContrast
import com.org.huanjianmvp.Presenter.InspectAndPhotoPresenter

/**
 * Created by Administrator on 2020/8/24.
 */

class InspectAndPhotoActivityModel(mPresenter: InspectAndPhotoPresenter?): BaseActivityModel<InspectAndPhotoPresenter, InspectAndPhotoContrast.Model>(mPresenter){
    override fun getContract(): InspectAndPhotoContrast.Model {
        return object : InspectAndPhotoContrast.Model{

        }
    }

}
