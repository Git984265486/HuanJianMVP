package com.org.huanjianmvp.Activity

import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.org.huanjianmvp.Adapter.InspectAdapter
import com.org.huanjianmvp.Base.BaseFragment
import com.org.huanjianmvp.Contrast.InspectionContrast
import com.org.huanjianmvp.Domain.UserData
import com.org.huanjianmvp.Domain.inspectCheck
import com.org.huanjianmvp.Presenter.InspectionPresenter
import com.org.huanjianmvp.R
import com.org.huanjianmvp.Utils.StaticValues
import com.org.huanjianmvp.Utils.StringUtils
import kotlinx.android.synthetic.main.inspection.*

/**
 * 外检检验项页面处理
 * **/
class Inspection : BaseFragment<InspectionPresenter,InspectionContrast.ViewAndPresenter>() {

    var user : UserData? = null
    var mapData = HashMap<String,String>()

    override fun getContract(): InspectionContrast.ViewAndPresenter {
        return object : InspectionContrast.ViewAndPresenter{

        }
    }

    override fun createPresenter(): InspectionPresenter {
        return InspectionPresenter()
    }

    override fun getViewID(): Int {
        return R.layout.inspection
    }

    override fun initViewUI() {

        var list = ArrayList<inspectCheck>()

        for (i in StaticValues.InspectionValues.indices) {
            var check = inspectCheck()
            check.title = StaticValues.InspectionValues[i]
            check.result = "是"
            check.isCheck = true
            list.add(check)
        }

        var adapter = InspectAdapter(list)

        inspectRecycler.layoutManager = LinearLayoutManager(this.context)
        inspectRecycler.adapter = adapter

        mapData = adapter.spinnerMap    //获取适配器InspectAdapter中的数据
    }

    override fun initListener() {

        inspectCommit.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.inspectCommit ->{
                for (i in 0..(mapData.size - 1)){
                    Log.e("【提交结果数据】",mapData?.get(StringUtils.inspectMapKey(StaticValues.InspectionValues[i])) +"\t\t【i的值】\t\t"+ i.toString())
                }
            }
        }
    }

    override fun destroy() {
        activity.finish()
    }

    override fun initDatas() {
        user = activity.intent.getSerializableExtra("data") as UserData
        user?.ShowLog()
        Log.e("外检检验项页面打印数据","【完毕】")
    }

    override fun <ERROR : Any?> responseError(error: ERROR, throwable: Throwable?) {

    }

}
