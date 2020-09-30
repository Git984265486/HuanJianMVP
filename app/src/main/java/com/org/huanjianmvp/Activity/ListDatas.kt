package com.org.huanjianmvp.Activity

import `in`.srain.cube.views.ptr.PtrDefaultHandler
import `in`.srain.cube.views.ptr.PtrFrameLayout
import `in`.srain.cube.views.ptr.PtrHandler
import `in`.srain.cube.views.ptr.header.MaterialHeader
import `in`.srain.cube.views.ptr.util.PtrLocalDisplay
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.KeyEvent
import android.view.View
import cn.pedant.SweetAlert.SweetAlertDialog
import com.org.huanjianmvp.Adapter.ListDatasAdapter
import com.org.huanjianmvp.Base.BaseActivity
import com.org.huanjianmvp.Contrast.ListDatasContrast
import com.org.huanjianmvp.Domain.UserData
import com.org.huanjianmvp.Presenter.ListDatasActivityPresenter
import com.org.huanjianmvp.R
import com.org.huanjianmvp.Utils.AlertDialogUtils
import kotlinx.android.synthetic.main.list_datas.*

/**
 * 登录后数据展示页面
 * **/

class ListDatas : BaseActivity<ListDatasActivityPresenter, ListDatasContrast.ViewAndPresenter>() {

    var arrayList = arrayListOf<UserData>()
    var dialogUtils = AlertDialogUtils(this)

    /**【实现契约类中的接口】**/
    override fun getContract(): ListDatasContrast.ViewAndPresenter {
        return object : ListDatasContrast.ViewAndPresenter{
            override fun requestExit(alert: SweetAlertDialog) {
                mPresenter.contract.requestExit(alert)
            }

            override fun responseExit(isExit: Boolean?) {
                if (isExit==true){
                    this@ListDatas.finish()
                }
            }

            /**【请求数据】**/
            override fun requestData() {
                mPresenter.contract.requestData()
            }

            /**【响应请求数据】**/
            override fun responseData(list: List<UserData>) {
                arrayList = list as ArrayList<UserData>
                contract.requestRefresh(arrayList,recycler_View,this@ListDatas, Function::class.java)
            }

            /**【请求刷新数据】**/
            override fun requestRefresh(list: List<UserData>, rv: RecyclerView, context: Context, clas: Class<*>) {
                mPresenter.contract.requestRefresh(list,rv,context,clas)
            }

            /**【响应刷新、重载数据】**/
            override fun responseRefresh(adapter: ListDatasAdapter) {
                recycler_View.adapter = adapter
            }

            /**【查找请求】**/
            override fun requestFind(string: String) {
                mPresenter.contract.requestFind(string)
            }

            /**【响应查找结果】**/
            override fun responseFind(list: List<UserData>) {
                arrayList = list as ArrayList<UserData>
                contract.requestRefresh(arrayList,recycler_View,this@ListDatas, Function::class.java)
            }
        }
    }

    /**【实例化P层】**/
    override fun createPresenter(): ListDatasActivityPresenter {
        return ListDatasActivityPresenter()
    }

    /**【加载布局文件】**/
    override fun getViewID(): Int {
        return R.layout.list_datas
    }

    /**【初始化UI组件】**/
    override fun initViewUI() {
        application.addActivity(this@ListDatas)

        /**【下拉刷新】**/
        val header = MaterialHeader(this)
        header.setPadding(0, PtrLocalDisplay.dp2px(15f), 0, 0)
        mPtrFrame.headerView = header
        mPtrFrame.addPtrUIHandler(header)
        /**【绑定下拉操作事务】**/
        mPtrFrame.setPtrHandler(object : PtrHandler{
            /**【加载数据时触发，可在这里做线程获取数据等操作】**/
            override fun onRefreshBegin(frame: PtrFrameLayout?) {
                editTextFind.text = null                                              //刷新后输入框设为null
                contract.requestData()                                                //请求数据
                mPtrFrame.refreshComplete()                                           //刷新完成
            }
            /**【检查是否可以执行下来刷新，比如列表为空或者列表第一项在最上面时】**/
            override fun checkCanDoRefresh(frame: PtrFrameLayout?, content: View?, header: View?): Boolean {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header)
            }
        })
        /**【列出数据】**/
        var layoutManager = LinearLayoutManager(this)
        recycler_View.layoutManager = layoutManager
    }

    /**【点击监听】**/
    override fun initListener() {
        btnFind.setOnClickListener(this)
    }

    /**【Button点击实现事件】**/
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnFind ->{
                var inputStr = editTextFind.text.toString().trim()
                if (!inputStr.equals("")){
                    contract.requestFind(inputStr)
                }else{
                    dialogUtils.AlertTitle("查找的内容不能为空！","warning")
                }
            }
        }
    }

    /**【销毁时执行】**/
    override fun destroy() {
        if (dialogUtils!=null){
            dialogUtils.dismissDialog()
        }
        this.finish()
    }

    /**【数据初始化】**/
    override fun initDatas() {
        contract.requestData()
    }

    /**【错误处理】**/
    override fun <ERROR : Any?> responseError(error: ERROR, throwable: Throwable?) {
        Log.e("eee","responseError")
    }

    /**【重写返回键】 */
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.repeatCount == 0) {
            contract.requestExit(dialogUtils.getAlertDialog("warning"))
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}
