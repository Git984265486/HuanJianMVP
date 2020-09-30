package com.org.huanjianmvp.Presenter

import android.content.SharedPreferences
import com.org.huanjianmvp.Activity.Setting
import com.org.huanjianmvp.Base.BaseActivityPresenter
import com.org.huanjianmvp.Contrast.SettingContrast
import com.org.huanjianmvp.Model.SettingModel

/**
 * Created by Administrator on 2020/8/28.
 */
class SettingPresenter: BaseActivityPresenter<Setting,SettingModel,SettingContrast.ViewAndPresenter>() {
    override fun getContract(): SettingContrast.ViewAndPresenter {
        return object : SettingContrast.ViewAndPresenter{
            override fun requestSave(map: Map<String, String> , preferences: SharedPreferences) {
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

    override fun createModel(): SettingModel {
        return SettingModel(this)
    }
}