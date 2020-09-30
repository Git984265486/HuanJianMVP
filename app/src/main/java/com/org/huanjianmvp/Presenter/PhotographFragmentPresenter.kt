package com.org.huanjianmvp.Presenter

import android.content.Context
import android.content.Intent
import com.org.huanjianmvp.Activity.Photograph
import com.org.huanjianmvp.Base.BaseFragmentPresenter
import com.org.huanjianmvp.Contrast.PhotographContrast
import com.org.huanjianmvp.Domain.UserData
import com.org.huanjianmvp.Model.PhotographFragmentModel

/**
 * Created by Administrator on 2020/8/24.
 */
class PhotographFragmentPresenter : BaseFragmentPresenter<Photograph,PhotographFragmentModel,PhotographContrast.ViewAndPresenter>(){
    override fun getContract(): PhotographContrast.ViewAndPresenter {
        return object : PhotographContrast.ViewAndPresenter{
            override fun requestPhotoData(fileName : String , context: Context) {
                mModel.contract.actionPhotoData(fileName , context)
            }

            override fun responseOnLongClick(intent: Intent) {
                weakView.get()!!.contract.responseOnLongClick(intent)
            }

            override fun requestOnLongClick(context: Context, user: UserData) {
                mModel.contract.actionOnLongClick(context,user)
            }

            override fun requestOnClick(context: Context, user: UserData) {
                mModel.contract.actionOnClick(context,user)
            }

            override fun requestData(user : UserData) {
                mModel.contract.actionGetData(user)
            }

            override fun responseData(arrayList: ArrayList<UserData> , arraySpinnerStr: ArrayList<String>) {
                weakView.get()!!.contract.responseData(arrayList , arraySpinnerStr)
            }

            override fun requestAddImg(arrayList: ArrayList<UserData>, strSelect: String) {
                mModel.contract.actionAddImage(arrayList,strSelect)
            }

            override fun responseAddImg(boolean: Boolean , arrayLists: ArrayList<UserData>) {
                weakView.get()!!.contract.responseAddImg(boolean , arrayLists)
            }

        }
    }

    override fun createModel(): PhotographFragmentModel {
        return PhotographFragmentModel(this)
    }
}