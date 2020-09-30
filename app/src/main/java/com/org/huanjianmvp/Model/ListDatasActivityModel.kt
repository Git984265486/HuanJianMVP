package com.org.huanjianmvp.Model

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import cn.pedant.SweetAlert.SweetAlertDialog
import com.org.huanjianmvp.Adapter.ListDatasAdapter
import com.org.huanjianmvp.Base.BaseActivityModel
import com.org.huanjianmvp.Contrast.ListDatasContrast
import com.org.huanjianmvp.Domain.UserData
import com.org.huanjianmvp.Presenter.ListDatasActivityPresenter

/**
 * Created by Administrator on 2020/8/19.
 */
class ListDatasActivityModel(mPresenter: ListDatasActivityPresenter?) : BaseActivityModel<ListDatasActivityPresenter, ListDatasContrast.Model>(mPresenter) {

    override fun getContract(): ListDatasContrast.Model {
        return object : ListDatasContrast.Model{

            /**【请求退出处理】**/
            override fun exitAction(alert: SweetAlertDialog) {
                alert.setTitle("是否退出当前软件?")
                alert.confirmText = "确定"
                alert.cancelText = "取消"
                alert.setConfirmClickListener {
                    presenter.contract.responseExit(true)
                }
                alert.setCancelClickListener {
                    presenter.contract.responseExit(false)
                    alert.dismiss()
                }
                alert.show()
            }

            /**【数据源、初始数据】**/
            override fun dataSource(){
                try {

                }catch (exception:Exception){

                }

                var arrayList = arrayListOf<UserData>()     //创建一个空数组

                //模拟数据
                var user1 = UserData()
                user1.age = "23"
                user1.sex = "男"
                user1.userName = "张三"

                var user2 = UserData()
                user2.age = "30"
                user2.sex = "女"
                user2.userName = "李四"

                var user3 = UserData()
                user3.age = "25"
                user3.sex = "保密"
                user3.userName = "刘柳"

                arrayList.add(user1)
                arrayList.add(user2)
                arrayList.add(user3)

                presenter.contract.responseData(arrayList)      //响应请求数据
            }

            /**【下拉刷新数据处理、并绑定每一项的点击事件】**/
            override fun refreshData(list: List<UserData>, rv: RecyclerView, context: Context, clas: Class<*>){
                var adapter = ListDatasAdapter(list,rv)
                adapter.setOnItemClickListener{parent, view, position, data ->
                    var intent = Intent(context,clas)
                    var bundle = Bundle()
                    bundle.putSerializable("data",data)
                    intent.putExtras(bundle)
                    context.startActivity(intent)
                }
                presenter.contract.responseRefresh(adapter)     //响应刷新重载数据
            }

            /**【关键字查找数据】**/
            override fun dataFind(string: String) {
                Log.e("拿到的查找数据",string)
                var returnList = arrayListOf<UserData>()    //模仿返回数据
                if (string.equals("admin")){
                    var user1 = UserData()
                    user1.age = "保"
                    user1.sex = "保密"
                    user1.userName = "保密吧"
                    returnList.add(user1)
                }
                presenter.contract.responseFind(returnList)     //响应查找数据
            }
        }
    }


}