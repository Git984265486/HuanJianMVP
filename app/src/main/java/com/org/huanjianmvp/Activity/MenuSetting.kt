package com.org.huanjianmvp.Activity


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import com.org.huanjianmvp.Base.BaseActivity
import com.org.huanjianmvp.Contrast.MenuSettingContrast
import com.org.huanjianmvp.Domain.UserData
import com.org.huanjianmvp.Presenter.MenuSettingPresenter
import com.org.huanjianmvp.R
import kotlinx.android.synthetic.main.base_setting.*
import java.util.*

class MenuSetting : BaseActivity<MenuSettingPresenter,MenuSettingContrast.ViewAndPresenter>() {

    var map = HashMap<String,String>()


    override fun getContract(): MenuSettingContrast.ViewAndPresenter {
        return object : MenuSettingContrast.ViewAndPresenter{
            override fun requestSave(map: Map<String, String>, preferences: SharedPreferences) {
                mPresenter.contract.requestSave(map,preferences)
            }

            override fun requestInitData(preferences: SharedPreferences) {
                mPresenter.contract.requestInitData(preferences)
            }

            override fun responseInitData(map: Map<String, String>) {
                if (map.get("radioWJ") == "1"){
                    radioWJ_Select.isChecked = true
                }else{
                    radioWJ_Default.isChecked = true
                }

                if (map.get("radioDP") == "1"){
                    radioDP_Select.isChecked = true
                }else{
                    radioDP_Default.isChecked = true
                }

                if (map.get("radioXHPZ") == "1"){
                    radioXHPZ_Select.isChecked = true
                }else{
                    radioXHPZ_Default.isChecked = true
                }

                if (map.get("radioDTDP") == "1"){
                    radioDTDP_Select.isChecked = true
                }else{
                    radioDTDP_Default.isChecked = true
                }

                if (map.get("radioSCFS") == "1"){
                    radioSCFS_Select.isChecked = true
                }else{
                    radioSCFS_Default.isChecked = true
                }

                if (map.get("radioSCMS") == "1"){
                    radioSCMS_Select.isChecked = true
                }else{
                    radioSCMS_Default.isChecked = true
                }

                if (map.get("radioSJTK") == "1"){
                    radioSJTK_Select.isChecked = true
                }else{
                    radioSJTK_Default.isChecked = true
                }

                if (map.get("radioZPZD") == "1"){
                    radioZPZD_Select.isChecked = true
                }else{
                    radioZPZD_Default.isChecked = true
                }
            }

        }
    }

    override fun createPresenter(): MenuSettingPresenter {
        return MenuSettingPresenter()
    }

    override fun getViewID(): Int {
        return R.layout.base_setting
    }

    override fun initViewUI() {
        application.addActivity(this@MenuSetting)
    }

    override fun initListener() {
        saveBaseSetting.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.saveBaseSetting->{
                var preferences = getSharedPreferences("MenuSetting",0)
                if (radioWJ_Select.isChecked){         //外检通道号
                    map.put("radioWJ","1")
                }else{
                    map.put("radioWJ","0")
                }

                if (radioDP_Select.isChecked){        //地盘通道号
                    map.put("radioDP","1")
                }else{
                    map.put("radioDP","0")
                }

                if (radioXHPZ_Select.isChecked){         //循环拍照
                    map.put("radioXHPZ","1")
                }else{
                    map.put("radioXHPZ","0")
                }

                if (radioDTDP_Select.isChecked){       //动态地盘
                    map.put("radioDTDP","1")
                }else{
                    map.put("radioDTDP","0")
                }

                if (radioSCFS_Select.isChecked){       //数据上传方式
                    map.put("radioSCFS","1")
                }else{
                    map.put("radioSCFS","0")
                }

                if (radioSCMS_Select.isChecked){       //照片上传模式
                    map.put("radioSCMS","1")
                }else{
                    map.put("radioSCMS","0")
                }

                if (radioSJTK_Select.isChecked){       //时间调控
                    map.put("radioSJTK","1")
                }else{
                    map.put("radioSJTK","0")
                }

                if (radioZPZD_Select.isChecked){       //驻坡制动
                    map.put("radioZPZD","1")
                }else{
                    map.put("radioZPZD","0")
                }
                contract.requestSave(map,preferences)
                var intentFunction = Intent(this@MenuSetting,Function::class.java)
                var bundle = Bundle()
                bundle.putSerializable("data",intent.getSerializableExtra("data") as UserData)
                intentFunction.putExtras(bundle)
                startActivity(intentFunction)
                this.finish()
            }
        }
    }

    override fun destroy() {

    }

    override fun initDatas() {
        var preferences = getSharedPreferences("MenuSetting",0)
        contract.requestInitData(preferences)
    }

    override fun <ERROR : Any?> responseError(error: ERROR, throwable: Throwable?) {

    }

}
