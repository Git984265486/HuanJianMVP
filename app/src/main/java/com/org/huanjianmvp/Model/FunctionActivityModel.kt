package com.org.huanjianmvp.Model

import com.org.huanjianmvp.Base.BaseActivityModel
import com.org.huanjianmvp.Contrast.FunctionContrast
import com.org.huanjianmvp.Presenter.FunctionActivityPresenter

/**
 * Created by Administrator on 2020/8/21.
 */

open class FunctionActivityModel(mPresenter: FunctionActivityPresenter?) : BaseActivityModel<FunctionActivityPresenter, FunctionContrast.Model>(mPresenter) {

    override fun getContract(): FunctionContrast.Model {
        return object : FunctionContrast.Model {

        }
    }
}

