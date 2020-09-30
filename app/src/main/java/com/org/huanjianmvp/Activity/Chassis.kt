package com.org.huanjianmvp.Activity

import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.org.huanjianmvp.Adapter.AJCheckAdapter
import com.org.huanjianmvp.Base.BaseActivity
import com.org.huanjianmvp.Contrast.ChassisContrast
import com.org.huanjianmvp.Domain.inspectCheck
import com.org.huanjianmvp.Presenter.ChassisPresenter
import com.org.huanjianmvp.R
import com.org.huanjianmvp.Utils.StaticValues
import kotlinx.android.synthetic.main.chassis.*

class Chassis : BaseActivity<ChassisPresenter , ChassisContrast.ViewAndPresenter>() {

    var chassisAdapter: AJCheckAdapter? = null

    override fun getContract(): ChassisContrast.ViewAndPresenter {
        return object : ChassisContrast.ViewAndPresenter{

        }
    }

    override fun createPresenter(): ChassisPresenter {
        return ChassisPresenter()
    }

    override fun getViewID(): Int {
        return R.layout.chassis
    }

    override fun initViewUI() {

        chassisEnd.isEnabled = false        //提交按钮不可点击

        chassisRecycler.layoutManager = LinearLayoutManager(this@Chassis)

        var dataList = ArrayList<inspectCheck>()    //适配器
        for (i in StaticValues.chassisDynamics.indices) {
            var check = inspectCheck()
            check.title = StaticValues.chassisDynamics[i]
            check.result = "合格"
            check.isCheck = true
            dataList.add(check)
        }

        chassisAdapter = AJCheckAdapter(dataList)

        chassisRecycler.adapter = chassisAdapter
    }

    override fun initListener() {
        chassisBegin.setOnClickListener(this)
        chassisEnd.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.chassisBegin->{
                chassisEnd.isEnabled = true     //提交按钮可点击
            }
            R.id.chassisEnd->{
                Log.e("map中数据总数","\t\t" + chassisAdapter?.spinnerMap!!.size.toString())
            }
        }
    }

    override fun destroy() {

    }

    override fun initDatas() {

    }

    override fun <ERROR : Any?> responseError(error: ERROR, throwable: Throwable?) {

    }

}
