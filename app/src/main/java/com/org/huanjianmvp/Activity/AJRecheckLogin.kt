package com.org.huanjianmvp.Activity

import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.org.huanjianmvp.Adapter.AJBaseInfoAdapter
import com.org.huanjianmvp.Adapter.AJRecheckLoginAdapter
import com.org.huanjianmvp.Base.BaseFragment
import com.org.huanjianmvp.Contrast.AJRecheckLoginContrast
import com.org.huanjianmvp.Domain.inspectCheck
import com.org.huanjianmvp.Presenter.AJRecheckLoginPresenter
import com.org.huanjianmvp.R
import com.org.huanjianmvp.Utils.StaticValues
import com.org.huanjianmvp.Utils.StringUtils
import kotlinx.android.synthetic.main.aj_recheck_login.*

/**
 * 安检的【复检登录】页
 * **/

class AJRecheckLogin : BaseFragment<AJRecheckLoginPresenter,AJRecheckLoginContrast.ViewAndPresenter>() {

    var carProvince: String? = null
    var selectType: String? = null
    var isForceCheck: String? = null
    var pdaAdapter:  AJRecheckLoginAdapter? = null          //PDA检测适配器
    var instrumentAdapter:  AJRecheckLoginAdapter? = null   //仪器检测适配器

    override fun getContract(): AJRecheckLoginContrast.ViewAndPresenter {
        return object : AJRecheckLoginContrast.ViewAndPresenter{

        }
    }

    override fun createPresenter(): AJRecheckLoginPresenter {
        return AJRecheckLoginPresenter()
    }

    override fun getViewID(): Int {
        return R.layout.aj_recheck_login
    }

    override fun initViewUI() {
        var carProvince = ArrayAdapter<String>(this.context,R.layout.spinner_textview, StringUtils.strToArrayList(StaticValues.provinceName))
        recheckLoginCarProvince.adapter = carProvince
        var carTypeAdapter = ArrayAdapter<String>(this.context,R.layout.spinner_textview, StringUtils.strToArrayList(StaticValues.carType))
        recheckLoginCarType.adapter = carTypeAdapter
        forceCheckN.isChecked = true  //单选默认 否

        recheckPDAContent.layoutManager = LinearLayoutManager(this.context)
        recheckPDAContent.adapter = pdaAdapter

        recheckInstrument.layoutManager = LinearLayoutManager(this.context)
        recheckInstrument.adapter = instrumentAdapter
    }

    override fun initListener() {
        recheckLoginFind.setOnClickListener(this)
        recheckLoginBtn.setOnClickListener(this)
        /**【绑定spinner选择事件】**/
        recheckLoginCarProvince.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                carProvince = StaticValues.provinceName[i]
                Log.e("省份缩写",carProvince + "\t\t选择的position:\t\t" + i)
            }
            override fun onNothingSelected(adapterView: AdapterView<*>) {}
        }
        /**【绑定spinner选择事件】**/
        recheckLoginCarType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                selectType = StaticValues.carType[i]
                Log.e("车辆类型",selectType + "\t\t选择的position:\t\t" + i)
            }
            override fun onNothingSelected(adapterView: AdapterView<*>) {}
        }
        /**【单选事件】**/
        forceCheck.setOnCheckedChangeListener { radioGroup, position ->
            when(position){
                forceCheckN.id ->{
                    isForceCheck = "NO"
                }
                forceCheckY.id->{
                    isForceCheck = "YES"
                }
            }
        }
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.recheckLoginFind->{
                Log.e("是否强制路试",isForceCheck + "..")
            }
            R.id.recheckLoginBtn->{
                var pdaArray = pdaAdapter!!.listRecheck                 //PDA检测内容
                var instrumentArray = instrumentAdapter!!.listRecheck   //仪器检测内容
                var finalArray = ArrayList<String>()                    //最终提交的内容
                /**【合并二者的内容】**/
                for (i in pdaArray.indices){
                    if (!finalArray.contains(pdaArray[i])){     //如果 finalArray 中不包含 pdaArray[i]
                        finalArray.add(pdaArray[i])
                    }
                }
                for (i in instrumentArray.indices){
                    if (!finalArray.contains(instrumentArray[i])){     //如果 finalArray 中不包含 pdaArray[i]
                        finalArray.add(instrumentArray[i])
                    }
                }
                pdaAdapter!!.listArrayListContent(finalArray)
                Log.e("总共有数据" , finalArray.size.toString() + "条")
            }
        }
    }

    override fun destroy() {

    }

    override fun initDatas() {
        /**【单选默认值】**/
        if (forceCheckN.isChecked){
            isForceCheck = "NO"
        }else{
            isForceCheck = "YES"
        }
        /**【适配器初始化】**/
        var listPDA = ArrayList<inspectCheck>()
        for (i in StaticValues.PADCheck.indices){
            var inspectPDA = inspectCheck()
            inspectPDA.title = StaticValues.PADCheck[i]
            inspectPDA.isCheck = true
            listPDA.add(inspectPDA)
        }
        pdaAdapter = AJRecheckLoginAdapter(listPDA ,activity)

        var listInstrument = ArrayList<inspectCheck>()
        for (i in StaticValues.instrumentCheck.indices){
            var inspectInstrument = inspectCheck()
            inspectInstrument.title = StaticValues.instrumentCheck[i]
            inspectInstrument.isCheck = true
            listInstrument.add(inspectInstrument)
        }
        instrumentAdapter = AJRecheckLoginAdapter(listInstrument , activity)
    }

    override fun <ERROR : Any?> responseError(error: ERROR, throwable: Throwable?) {

    }


}
