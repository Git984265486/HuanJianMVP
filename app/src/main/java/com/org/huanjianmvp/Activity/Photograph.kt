package com.org.huanjianmvp.Activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.org.huanjianmvp.Adapter.PhotoAdapter
import com.org.huanjianmvp.Adapter.PhotoClickListener
import com.org.huanjianmvp.Adapter.PhotoLongClickListener
import com.org.huanjianmvp.Base.BaseFragment
import com.org.huanjianmvp.Contrast.PhotographContrast
import com.org.huanjianmvp.Domain.UserData
import com.org.huanjianmvp.Presenter.PhotographFragmentPresenter
import com.org.huanjianmvp.R
import com.org.huanjianmvp.Utils.SQLiteFunction
import com.org.huanjianmvp.Utils.StaticValues
import kotlinx.android.synthetic.main.photograph.*

/**
 * 外检拍照页面
 **/
class Photograph : BaseFragment<PhotographFragmentPresenter,PhotographContrast.ViewAndPresenter>() {

    var user : UserData? = null         //传递过来的对象数据
    var arrayList = ArrayList<UserData>()
    var spinnerVal = arrayOf("车辆VIN照", "车辆排气照","燃油蒸发控制系统照","污染控制装置照")
    var arraySpinner = ArrayList<String>()      //spinner下拉添加的图片
    var strSelect : String = ""                 //选择要添加的图片
    var photoAdapter : PhotoAdapter? = null     //recyclerView适配器

    var fileName : String = ""      //文件名字【流水号 + 照片名】

    override fun getContract(): PhotographContrast.ViewAndPresenter {
        return object : PhotographContrast.ViewAndPresenter{
            override fun requestPhotoData(fileName : String , context: Context) {
                mPresenter.contract.requestPhotoData(fileName , context)
            }

            override fun responseOnLongClick(intent: Intent) {
                startActivityForResult(intent,103)       //启动相机
            }

            override fun requestOnLongClick(context: Context, user: UserData) {
                mPresenter.contract.requestOnLongClick(context,user)
            }

            override fun requestOnClick(context: Context, user: UserData) {
                mPresenter.contract.requestOnClick(activity,user)
            }

            override fun requestData(user : UserData) {
                mPresenter.contract.requestData(user)
            }

            override fun responseData(arrayLists: ArrayList<UserData> , arraySpinnerStr: ArrayList<String>) {
                arrayList = arrayLists
                arraySpinner = arraySpinnerStr
            }

            override fun requestAddImg(arrayList: ArrayList<UserData>, strSelect: String) {
                mPresenter.contract.requestAddImg(arrayList,strSelect)
            }

            override fun responseAddImg(boolean: Boolean  , arrayLists: ArrayList<UserData>) {
                if (!boolean){
                    arrayList = arrayLists
                    photoAdapter = PhotoAdapter(arrayList,recycler_View,activity)
                    /**【绑定点击事件】**/
                    photoAdapter?.setOnPhotoClickListener(PhotoClickListener { parent, view, position, data ->
                        contract.requestOnClick(activity , data)                    //点击事件处理绑定
                        fileName = data!!.age + "_" + data!!.userName + ".jpg"      //文件名字、原始文件，未添加文字水印【流水号 + 照片名】
                    })
                    /**【绑定长按事件】**/
                    photoAdapter?.setOnPhotoLongClickListener(PhotoLongClickListener{parent, view, position, data ->
                        contract.requestOnLongClick(activity,data)                  //长按事件处理绑定
                        fileName = data!!.age + "_" + data!!.userName + ".jpg"      //文件名字、原始文件，未添加文字水印【流水号 + 照片名】

                    })
                    recycler_View.adapter = photoAdapter
                }
            }
        }
    }

    override fun createPresenter(): PhotographFragmentPresenter {
        return PhotographFragmentPresenter()
    }

    override fun getViewID(): Int {
        return R.layout.photograph
    }

    override fun initViewUI() {
        /**【绑定适配器】**/
        recycler_View.adapter = photoAdapter

        recycler_View.layoutManager = GridLayoutManager(activity, 2)

        var adapter = ArrayAdapter<String>(activity,R.layout.spinner_textview,spinnerVal)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerImg.adapter = adapter

        funCarNumber.text = user?.userName      //车牌号码
        funCarType.text = user?.age             //品牌型号
        funSerialNumber.text = user?.userName   //检验流水号
        funCarNumType.text = user?.sex          //号牌种类
    }


    override fun initListener() {
        btnAdd.setOnClickListener(this)
        /**【绑定spinner选择事件】**/
        spinnerImg.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                strSelect = spinnerVal[i]
            }
            override fun onNothingSelected(adapterView: AdapterView<*>) {
            }
        }
        /**【绑定图片点击事件】**/
        photoAdapter?.setOnPhotoClickListener(PhotoClickListener { parent, view, position, data ->
            contract.requestOnClick(activity , data)                    //点击事件处理绑定
            fileName = data!!.age + "_" + data!!.userName + ".jpg"      //文件名字、原始文件，未添加文字水印【流水号 + 照片名】
            //Log.e("图片点击事件名字","\t\t" + fileName)
        })
        /**【绑定图片长按事件】**/
        photoAdapter?.setOnPhotoLongClickListener(PhotoLongClickListener{parent, view, position, data ->
            contract.requestOnLongClick(activity,data)                  //长按事件处理绑定
            fileName = data!!.age + "_" + data!!.userName + ".jpg"      //文件名字、原始文件，未添加文字水印【流水号 + 照片名】
            //Log.e("图片长按事件名字","\t\t" + fileName)
        })
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.btnAdd ->{
                contract.requestAddImg( arrayList , strSelect )
            }
        }
    }

    override fun destroy() {
        activity.finish()
    }

    override fun initDatas() {

        user = activity.intent.getSerializableExtra("data") as UserData     //获取传递过来的对象数据
        user?.ShowLog()
        Log.e("外检拍照页面打印数据","【完毕】")

        contract.requestData(user!!)    //请求初始数据

        /**【初始化适配器】**/
        photoAdapter = PhotoAdapter(arrayList , recycler_View , activity)

    }

    /**【数据重新初始化、刷新数据】**/
    override fun onResume() {
        super.onResume()
        photoAdapter = PhotoAdapter(arrayList,recycler_View,activity)   //重新初始化适配器，刷新数据
        recycler_View.adapter = photoAdapter
        initListener()                              //初始化监听
    }

    override fun <ERROR : Any?> responseError(error: ERROR, throwable: Throwable?) {

    }

    /**【回调函数】**/
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            when(requestCode){
                103 ->{
                    contract.requestPhotoData(fileName , activity)      //拍照返回数据处理

                    var lite = SQLiteFunction(activity,StaticValues.SQLiteName,null,StaticValues.SQLiteVersion)
                    lite.updateStatus(fileName , "1")       //更新数据库中图片上传状态
                    lite.LogData()

                    recycler_View.adapter = photoAdapter           //重新加载数据
                }
            }
        }
    }
}
