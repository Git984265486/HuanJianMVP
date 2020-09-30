package com.org.huanjianmvp.Model

import android.content.SharedPreferences
import com.org.huanjianmvp.Base.BaseActivityModel
import com.org.huanjianmvp.Contrast.MenuSettingContrast
import com.org.huanjianmvp.Presenter.MenuSettingPresenter

/**
 * Created by Administrator on 2020/8/31.
 */
class MenuSettingModel(mPresenter : MenuSettingPresenter?) : BaseActivityModel<MenuSettingPresenter,MenuSettingContrast.Model>(mPresenter) {
    override fun getContract(): MenuSettingContrast.Model {
        return object : MenuSettingContrast.Model{
            override fun saveAction(map: Map<String, String>, preferences: SharedPreferences) {
                var editor = preferences.edit()
                editor.putString("radioWJ",map.get("radioWJ"))          //外检通道号
                editor.putString("radioDP",map.get("radioDP"))          //底盘通道号
                editor.putString("radioXHPZ",map.get("radioXHPZ"))      //循环拍照
                editor.putString("radioDTDP",map.get("radioDTDP"))      //动态地盘
                editor.putString("radioSCFS",map.get("radioSCFS"))      //数据上传方式
                editor.putString("radioSCMS",map.get("radioSCMS"))      //照片上传模式
                editor.putString("radioSJTK",map.get("radioSJTK"))      //时间调控
                editor.putString("radioZPZD",map.get("radioZPZD"))      //驻坡制动
                editor.commit()
            }

            override fun initDataAction(preferences: SharedPreferences) {
                var map = HashMap<String,String>()
                map.put("radioWJ",preferences.getString("radioWJ","0"))         //外检通道号
                map.put("radioDP",preferences.getString("radioDP","0"))         //底盘通道号
                map.put("radioXHPZ",preferences.getString("radioXHPZ","0"))     //循环拍照
                map.put("radioDTDP",preferences.getString("radioDTDP","0"))     //动态地盘
                map.put("radioSCFS",preferences.getString("radioSCFS","0"))     //数据上传方式
                map.put("radioSCMS",preferences.getString("radioSCMS","0"))     //照片上传模式
                map.put("radioSJTK",preferences.getString("radioSJTK","0"))     //时间调控
                map.put("radioZPZD",preferences.getString("radioZPZD","0"))     //驻坡制动
                presenter.contract.responseInitData(map)
            }

        }
    }
}