package com.org.huanjianmvp.Model

import com.org.huanjianmvp.Base.BaseFragmentModel
import com.org.huanjianmvp.Contrast.AJCheckContrast
import com.org.huanjianmvp.Presenter.AJCheckPresenter

/**
 *
 * Created by Administrator on 2020/9/4.
 */
class AJCheckModel(mPresenter: AJCheckPresenter) : BaseFragmentModel<AJCheckPresenter,AJCheckContrast.Model>(mPresenter) {
    override fun getContract(): AJCheckContrast.Model {
        return object : AJCheckContrast.Model{
            override fun actionData() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        }
    }
}