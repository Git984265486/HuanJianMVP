package com.org.huanjianmvp.Presenter

import android.content.SharedPreferences
import com.org.huanjianmvp.Activity.MenuSetting
import com.org.huanjianmvp.Base.BaseActivityPresenter
import com.org.huanjianmvp.Contrast.MenuSettingContrast
import com.org.huanjianmvp.Model.MenuSettingModel

/**
 * Created by Administrator on 2020/8/31.
 */
class MenuSettingPresenter : BaseActivityPresenter<MenuSetting,MenuSettingModel,MenuSettingContrast.ViewAndPresenter>(){
    override fun createModel(): MenuSettingModel {
        return MenuSettingModel(this)
    }

    override fun getContract(): MenuSettingContrast.ViewAndPresenter {
        return object : MenuSettingContrast.ViewAndPresenter{
            override fun requestSave(map: Map<String, String>, preferences: SharedPreferences) {
                mModel.contract.saveAction(map,preferences)
            }

            override fun requestInitData(preferences: SharedPreferences) {
                mModel.contract.initDataAction(preferences)
            }

            override fun responseInitData(map: Map<String, String>) {
                weakView.get()?.contract?.responseInitData(map)
            }

        }
    }


}