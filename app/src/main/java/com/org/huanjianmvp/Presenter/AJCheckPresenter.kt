package com.org.huanjianmvp.Presenter

import com.org.huanjianmvp.Activity.AJCheck
import com.org.huanjianmvp.Base.BaseFragmentPresenter
import com.org.huanjianmvp.Contrast.AJCheckContrast
import com.org.huanjianmvp.Model.AJCheckModel

/**
 * Created by Administrator on 2020/9/4.
 */
class AJCheckPresenter : BaseFragmentPresenter<AJCheck,AJCheckModel,AJCheckContrast.ViewAndPresenter>() {
    override fun getContract(): AJCheckContrast.ViewAndPresenter {
        return object : AJCheckContrast.ViewAndPresenter{
            override fun requestData() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun responseData(list: List<String>) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        }
    }

    override fun createModel(): AJCheckModel {
        return AJCheckModel(this)
    }
}