package com.org.huanjianmvp.Contrast

import android.content.Context
import android.support.v7.widget.RecyclerView
import cn.pedant.SweetAlert.SweetAlertDialog
import com.org.huanjianmvp.Adapter.ListDatasAdapter
import com.org.huanjianmvp.Domain.UserData

/**
 * Created by Administrator on 2020/8/19.
 */
interface ListDatasContrast {

    interface Model{
        //数据查找
        fun dataFind(string: String)

        //重新加载数据
        fun refreshData(list: List<UserData> , rv: RecyclerView , context: Context , clas: Class<*>)

        //获取数据源
        fun dataSource()

        //退出处理
        fun exitAction(alert: SweetAlertDialog)
    }

    interface ViewAndPresenter{

        /**【-----------View层的方法-----------】**/

        //请求获取数据
        fun requestData()

        //请求查找数据
        fun requestFind(string: String)

        //请求刷新重载数据
        fun requestRefresh(list: List<UserData> , rv: RecyclerView , context: Context , clas: Class<*>)

        //请求退出
        fun requestExit(alert: SweetAlertDialog)

        /**【-----------Presenter层的方法-----------】**/

        //响应请求数据
        fun responseData(list: List<UserData>)

        //响应查找数据
        fun responseFind(list: List<UserData>)

        //响应刷新重载数据
        fun responseRefresh(adapter: ListDatasAdapter)

        //响应请求退出
        fun responseExit(isExit: Boolean?)
    }

}