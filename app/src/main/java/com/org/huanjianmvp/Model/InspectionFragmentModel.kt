package com.org.huanjianmvp.Model

import com.org.huanjianmvp.Base.BaseFragmentModel
import com.org.huanjianmvp.Contrast.InspectionContrast
import com.org.huanjianmvp.Presenter.InspectionPresenter

/**
 * Created by Administrator on 2020/8/24.
 */
class InspectionFragmentModel(mPresenter: InspectionPresenter?): BaseFragmentModel<InspectionPresenter, InspectionContrast.Model>(mPresenter) {
    override fun getContract(): InspectionContrast.Model {
        return object : InspectionContrast.Model{

        }
    }
}