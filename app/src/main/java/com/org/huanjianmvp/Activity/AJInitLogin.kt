package com.org.huanjianmvp.Activity

import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.org.huanjianmvp.Adapter.AJBaseInfoAdapter
import com.org.huanjianmvp.Base.BaseFragment
import com.org.huanjianmvp.Contrast.AJInitLoginContrast
import com.org.huanjianmvp.Presenter.AJInitLoginPresenter
import com.org.huanjianmvp.R
import com.org.huanjianmvp.Utils.StaticValues
import com.org.huanjianmvp.Utils.StringUtils
import kotlinx.android.synthetic.main.aj_init_login.*

/**
 * 安检的【初检登录】页
 * **/

class AJInitLogin : BaseFragment<AJInitLoginPresenter , AJInitLoginContrast.ViewAndPresenter>() {

    var adapter:  AJBaseInfoAdapter? = null
    var carProvince: String? = null
    var carSeleType: String? = null


    override fun getContract(): AJInitLoginContrast.ViewAndPresenter {
        return object : AJInitLoginContrast.ViewAndPresenter{

        }
    }

    override fun createPresenter(): AJInitLoginPresenter {
        return AJInitLoginPresenter()
    }

    override fun getViewID(): Int {
        return R.layout.aj_init_login
    }

    override fun initViewUI() {
        var carProvince = ArrayAdapter<String>(this.context,R.layout.spinner_textview,StringUtils.strToArrayList(StaticValues.provinceName))
        carPosition.adapter = carProvince
        var carTypeAdapter = ArrayAdapter<String>(this.context,R.layout.spinner_textview,StringUtils.strToArrayList(StaticValues.carType))
        carType.adapter = carTypeAdapter

        recyclerBaseInfo.layoutManager = LinearLayoutManager(this.context)
        recyclerBaseInfo.adapter = adapter
        recyclerCheckInfo.layoutManager = LinearLayoutManager(this.context)
        recyclerCheckInfo.adapter = adapter
    }

    override fun initListener() {
        carBtnFind.setOnClickListener(this)
        initLoginBtn.setOnClickListener(this)
        /**【绑定spinner选择事件】**/
        carPosition.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                carProvince = StaticValues.provinceName[i]
                Log.e("省份缩写",carProvince + "\t\t选择的position:\t\t" + i)

            }
            override fun onNothingSelected(adapterView: AdapterView<*>) {
            }
        }
        /**【绑定spinner选择事件】**/
        carType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                carSeleType = StaticValues.carType[i]
                Log.e("省份缩写",carProvince + "\t\t选择的position:\t\t" + i)
            }
            override fun onNothingSelected(adapterView: AdapterView<*>) {
            }
        }
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.carBtnFind->{
                Log.e("省份缩写",carProvince + "-----" + carSeleType)
            }
            R.id.initLoginBtn->{

            }
        }
    }

    override fun destroy() {

    }

    override fun initDatas() {
        var list = ArrayList<String>()
        for (i in StaticValues.AJBaseInfoText.indices){
            list.add(StaticValues.AJBaseInfoText[i])
        }

        adapter = AJBaseInfoAdapter(list)
    }

    override fun <ERROR : Any?> responseError(error: ERROR, throwable: Throwable?) {

    }

}
