package com.org.huanjianmvp.Activity

import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.org.huanjianmvp.Adapter.AJPhotoAdapter
import com.org.huanjianmvp.Base.BaseFragment
import com.org.huanjianmvp.Contrast.AJCheckContrast
import com.org.huanjianmvp.Domain.UserData
import com.org.huanjianmvp.Presenter.AJPhotoPresenter
import com.org.huanjianmvp.R
import kotlinx.android.synthetic.main.aj_photo.*

/**
 * 安检拍照  页面
 * */

class AJPhoto : BaseFragment<AJPhotoPresenter,AJCheckContrast.ViewAndPresenter>() {

    var user : UserData? = null         //传递过来的对象数据
    var strSelect: String? = null
    var spinnerVal = arrayOf("车辆VIN照", "车辆排气照","燃油蒸发控制系统照","污染控制装置照")

    override fun getContract(): AJCheckContrast.ViewAndPresenter {
        return object : AJCheckContrast.ViewAndPresenter{
            override fun requestData() {

            }

            override fun responseData(list: List<String>) {

            }

        }
    }

    override fun createPresenter(): AJPhotoPresenter {
        return AJPhotoPresenter()
    }

    override fun getViewID(): Int {
        return R.layout.aj_photo
    }

    override fun initViewUI() {
        ajCarNumber.text = user!!.userName
        ajType.text = user!!.age
        ajSerialNumber.text = user!!.sex
        ajCarType.text = user!!.userName

        var spinnerAdapter = ArrayAdapter<String>(activity,R.layout.spinner_textview,spinnerVal)
        ajSpinnerImg.adapter = spinnerAdapter

        ajRecyclerView.layoutManager = GridLayoutManager(activity, 2)

        var arrayList =ArrayList<UserData>()

        //模拟数据
        var user1 = UserData()
        user1.age = user!!.userName
        user1.userName = "车辆VIN照"

        var user2 = UserData()
        user2.age = user!!.userName
        user2.userName = "车辆排气照"

        var user3 = UserData()
        user3.age = user!!.userName
        user3.userName = "燃油蒸发控制系统照"

        var user4 = UserData()
        user4.age = user!!.userName
        user4.userName = "张三"

        arrayList.add(user1)
        arrayList.add(user2)
        arrayList.add(user3)
        arrayList.add(user4)

        var photoAdapter = AJPhotoAdapter(arrayList , ajRecyclerView , activity)
        ajRecyclerView.adapter = photoAdapter
    }

    override fun initListener() {
        ajBtnAdd.setOnClickListener(this)

        ajSpinnerImg.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                strSelect = spinnerVal[i]
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {
            }
        }
    }

    override fun onClick(view: View?) {
        when(view!!.id){
            R.id.ajBtnAdd->{
                Log.e("添加的照片" , strSelect)
            }
        }
    }

    override fun destroy() {

    }

    override fun initDatas() {
        user = activity.intent.getSerializableExtra("data") as UserData     //获取传递过来的对象数据
    }

    override fun <ERROR : Any?> responseError(error: ERROR, throwable: Throwable?) {

    }

}
