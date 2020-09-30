package com.org.huanjianmvp.Model

import android.content.SharedPreferences
import com.org.huanjianmvp.Base.BaseActivityModel
import com.org.huanjianmvp.Contrast.SettingContrast
import com.org.huanjianmvp.Presenter.SettingPresenter

/**
 * Created by Administrator on 2020/8/28.
 */
class SettingModel(mPresenter:SettingPresenter?): BaseActivityModel<SettingPresenter,SettingContrast.Model>(mPresenter) {
    override fun getContract(): SettingContrast.Model {
        return object : SettingContrast.Model{

            /**【保存数据】**/
            override fun saveAction(map: Map<String, String> , preferences: SharedPreferences) {
                var editor = preferences.edit()
                editor.putString("IP", map.get("strIP").toString())
                editor.putString("FTP",map.get("strFTP").toString())
                editor.putString("JGBH",map.get("Number").toString())
                editor.putString("SXDDIP",map.get("Dispatch").toString())
                editor.putString("JCXDH",map.get("CheckNum").toString())
                if (map.get("upType").equals("存本地")){
                    editor.putInt("TPSC",0)
                }else{
                    editor.putInt("TPSC",1)
                }
                editor.commit()
            }

            /**【获取保存的数据】**/
            override fun initDataAction(preferences: SharedPreferences) {
                var map = HashMap<String,String>()
                map.put("strIP",preferences.getString("IP", ""))                //IP地址
                map.put("strFTP",preferences.getString("FTP", ""))              //FTP地址
                map.put("Number",preferences.getString("JGBH", ""))             //检测站编号
                map.put("Dispatch",preferences.getString("SXDDIP", ""))         //上线调度地址
                map.put("CheckNum",preferences.getString("JCXDH", ""))          //检验机构检测线代号
                map.put("upType",preferences.getInt("TPSC",0).toString()+"")             //上传方式
                presenter.contract.responseInitData(map)
            }
        }
    }
}