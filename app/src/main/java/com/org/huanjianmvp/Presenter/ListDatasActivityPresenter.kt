package com.org.huanjianmvp.Presenter

import android.content.Context
import android.support.v7.widget.RecyclerView
import cn.pedant.SweetAlert.SweetAlertDialog
import com.org.huanjianmvp.Activity.ListDatas
import com.org.huanjianmvp.Adapter.ListDatasAdapter
import com.org.huanjianmvp.Base.BaseActivityPresenter
import com.org.huanjianmvp.Contrast.ListDatasContrast
import com.org.huanjianmvp.Domain.UserData
import com.org.huanjianmvp.Model.ListDatasActivityModel

/**
 * Created by Administrator on 2020/8/19.
 */
class ListDatasActivityPresenter: BaseActivityPresenter<ListDatas, ListDatasActivityModel, ListDatasContrast.ViewAndPresenter>() {

    override fun getContract(): ListDatasContrast.ViewAndPresenter {
        return object : ListDatasContrast.ViewAndPresenter{
            override fun requestExit(alert: SweetAlertDialog) {
                mModel.contract.exitAction(alert)
            }

            override fun responseExit(isExit: Boolean?) {
                weakView.get()?.contract?.responseExit(isExit)
            }

            override fun requestData() {
                mModel.contract.dataSource()
            }

            override fun responseData(list: List<UserData>) {
                weakView.get()?.contract?.responseData(list)
            }

            override fun requestRefresh(list: List<UserData>, rv: RecyclerView, context: Context, clas: Class<*>) {
                mModel.contract.refreshData(list,rv,context,clas)
            }

            override fun responseRefresh(adapter: ListDatasAdapter) {
                weakView.get()?.contract?.responseRefresh(adapter)
            }


            override fun requestFind(string: String) {
                mModel.contract.dataFind(string)
            }

            override fun responseFind(list: List<UserData>) {
                weakView.get()?.contract?.responseFind(list)
            }

        }
    }

    override fun createModel(): ListDatasActivityModel {
        return ListDatasActivityModel(this)
    }
}