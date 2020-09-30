package com.org.huanjianmvp.Model

import com.org.huanjianmvp.Base.BaseFragmentModel
import com.org.huanjianmvp.Contrast.AJBaseInfoContrast
import com.org.huanjianmvp.Presenter.AJBaseInfoPresenter

/**
 * Created by Administrator on 2020/9/4.
 */
class AJBaseInfoModel(mPresenter: AJBaseInfoPresenter?) : BaseFragmentModel<AJBaseInfoPresenter,AJBaseInfoContrast.Model>(mPresenter) {
    override fun getContract(): AJBaseInfoContrast.Model {
        return object : AJBaseInfoContrast.Model{

        }
    }
}