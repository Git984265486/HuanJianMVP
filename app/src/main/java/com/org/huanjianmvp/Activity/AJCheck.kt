package com.org.huanjianmvp.Activity

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.org.huanjianmvp.Adapter.AJCheckAdapter
import com.org.huanjianmvp.Base.BaseFragment
import com.org.huanjianmvp.Contrast.AJCheckContrast
import com.org.huanjianmvp.Domain.inspectCheck
import com.org.huanjianmvp.Presenter.AJCheckPresenter
import com.org.huanjianmvp.R
import com.org.huanjianmvp.Utils.StaticValues
import kotlinx.android.synthetic.main.aj_check.*
import android.support.v7.widget.DividerItemDecoration
import com.gavin.com.library.StickyDecoration
import com.gavin.com.library.listener.GroupListener


/**
 * 安检外检   页面
 * */

class AJCheck : BaseFragment<AJCheckPresenter,AJCheckContrast.ViewAndPresenter>() {

    var adapter: AJCheckAdapter? = null
    var list : ArrayList<inspectCheck>? = null

    override fun getContract(): AJCheckContrast.ViewAndPresenter {
        return object : AJCheckContrast.ViewAndPresenter{
            override fun requestData() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun responseData(list: List<String>) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        }
    }

    override fun createPresenter(): AJCheckPresenter {
        return AJCheckPresenter()
    }

    override fun getViewID(): Int {
        return R.layout.aj_check
    }

    override fun initViewUI() {
        list = ArrayList()

        for (i in StaticValues.AJCheckContens.indices){
            var check = inspectCheck()
            check.title = StaticValues.AJCheckContens[i]
            check.result = "合格"
            check.isCheck = true
            list!!.add(check)
        }

        adapter = AJCheckAdapter(list!!)

        var groupListener = GroupListener { position ->
            gounpName(position)
        }

        var decoration = StickyDecoration.Builder.init(groupListener)
                .setGroupHeight(20).setGroupTextSize(15).setGroupTextColor(Color.parseColor("#373a3c")).build()
        /*.setGroupBackground(Color.parseColor("#0275d8"))*///设置背景色
        AJCheckItem.layoutManager = LinearLayoutManager(this.context)
        AJCheckItem.addItemDecoration(decoration)
        AJCheckItem.addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))  //添加Android自带的分割线
        AJCheckItem.adapter = adapter


    }

    fun gounpName(position: Int): String? {
        if (position == 0 ){
            return "车辆唯一性检查"
        }else if (position == 5){
            return "车辆特征参数检查"
        }else if (position == 7){
            return "车辆外观检查"
        }else if (position == 13){
            return "安全装置检查"
        }
        return null
    }

    override fun initListener() {
        AJCheckStart.setOnClickListener(this)
        AJCheckCommit.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view!!.id){
            /**【检验开始】**/
            R.id.AJCheckStart->{

            }
            /**【提交数据】**/
            R.id.AJCheckCommit->{
                var map = adapter!!.spinnerMap
                for (i in 0..(map.size-1)){
                    Log.e("提交的结果",map.get(i.toString()))
                }
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
