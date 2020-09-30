package com.org.huanjianmvp.Activity

import android.content.Intent
import android.content.SharedPreferences
import android.view.View
import android.widget.ArrayAdapter
import com.org.huanjianmvp.Base.BaseActivity
import com.org.huanjianmvp.Contrast.SettingContrast
import com.org.huanjianmvp.Login
import com.org.huanjianmvp.Presenter.SettingPresenter
import com.org.huanjianmvp.R
import kotlinx.android.synthetic.main.setting.*

/**
 * 程序设置参数页面
 * **/

class Setting : BaseActivity<SettingPresenter,SettingContrast.ViewAndPresenter>() {

    override fun getContract(): SettingContrast.ViewAndPresenter {
        return object : SettingContrast.ViewAndPresenter{
            override fun requestSave(map: Map<String, String> , preferences: SharedPreferences) {
                mPresenter.contract.requestSave(map,preferences)
            }

            override fun requestInitData(preferences: SharedPreferences) {
                mPresenter.contract.requestInitData(preferences)
            }

            override fun responseInitData(map: Map<String, String>) {
                editTextIP.setText(map.get("strIP"))
                editTextFTP.setText(map.get("strFTP"))
                editTextNumber.setText(map.get("Number"))
                editTextDispatch.setText(map.get("Dispatch"))
                editTextCheckNum.setText(map.get("CheckNum"))
                if (map.get("upType") == "0"){
                    uploadType.setSelection(0)
                }else{
                    uploadType.setSelection(1)
                }
            }
        }
    }

    override fun createPresenter(): SettingPresenter {
        return SettingPresenter()
    }

    override fun getViewID(): Int {
        return R.layout.setting
    }

    override fun initViewUI() {
        application.addActivity(this@Setting)
        var zpscfsdatas = arrayOf("存本地", "实时传")
        var adapterthree = ArrayAdapter<String>(this, R.layout.spinner_textview, zpscfsdatas)
        adapterthree.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        uploadType.adapter = adapterthree
    }

    override fun initListener() {
        settingSave.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.settingSave ->{
                var map = HashMap<String , String>()
                var preferences = getSharedPreferences("AppSettings", 0)
                map.put("strIP",editTextIP.text.toString())
                map.put("strFTP",editTextFTP.text.toString())
                map.put("Number",editTextNumber.text.toString())
                map.put("Dispatch",editTextDispatch.text.toString())
                map.put("CheckNum",editTextCheckNum.text.toString())
                map.put("upType",uploadType.selectedItem.toString())

                contract.requestSave(map ,preferences)

                var intent = Intent(this@Setting, Login::class.java)
                startActivity(intent)
                this.finish()
            }
        }
    }

    override fun destroy() {
        this@Setting.finish()
    }

    override fun initDatas() {
        var preferences = getSharedPreferences("AppSettings", 0)
        contract.requestInitData(preferences)
    }

    override fun <ERROR : Any?> responseError(error: ERROR, throwable: Throwable?) {

    }

}
