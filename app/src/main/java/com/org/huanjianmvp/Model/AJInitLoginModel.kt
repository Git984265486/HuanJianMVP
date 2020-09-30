package com.org.huanjianmvp.Model

import com.org.huanjianmvp.Base.BaseFragmentModel
import com.org.huanjianmvp.Contrast.AJInitLoginContrast
import com.org.huanjianmvp.Presenter.AJInitLoginPresenter

/**
 * Created by Administrator on 2020/9/17.
 */
class AJInitLoginModel(mPresenter: AJInitLoginPresenter): BaseFragmentModel<AJInitLoginPresenter , AJInitLoginContrast.Model>(mPresenter) {
    override fun getContract(): AJInitLoginContrast.Model {
        return object : AJInitLoginContrast.Model{

        }
    }
}