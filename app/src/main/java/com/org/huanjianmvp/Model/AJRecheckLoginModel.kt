package com.org.huanjianmvp.Model

import com.org.huanjianmvp.Base.BaseFragmentModel
import com.org.huanjianmvp.Contrast.AJRecheckLoginContrast
import com.org.huanjianmvp.Presenter.AJRecheckLoginPresenter

/**
 * Created by Administrator on 2020/9/17.
 */
class AJRecheckLoginModel(mPresenter: AJRecheckLoginPresenter): BaseFragmentModel<AJRecheckLoginPresenter,AJRecheckLoginContrast.Model>(mPresenter) {
    override fun getContract(): AJRecheckLoginContrast.Model {
        return object : AJRecheckLoginContrast.Model{

        }
    }
}