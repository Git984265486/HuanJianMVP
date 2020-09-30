package com.org.huanjianmvp.Contrast

import android.content.Context
import android.content.Intent
import com.org.huanjianmvp.Domain.UserData

/**
 * Created by Administrator on 2020/8/24.
 */
interface PhotographContrast {

    interface Model{

        /**【添加图片处理】**/
        fun actionAddImage(arrayList: ArrayList<UserData> , strSelect : String)

        /**【获取数据处理】**/
        fun actionGetData(user : UserData)

        /**【点击事件处理】**/
        fun actionOnClick(context: Context , user: UserData)

        /**【长按事件处理】**/
        fun actionOnLongClick(context: Context , user: UserData)

        /**【拍照返回数据处理】**/
        fun actionPhotoData(fileName : String , context: Context)

    }

    interface ViewAndPresenter{

        /**【请求获取数据事件】**/
        fun requestData(user : UserData)

        /**【响应获取数据事件】**/
        fun responseData(arrayList: ArrayList<UserData> , arraySpinnerStr: ArrayList<String>)

        /**【请求添加图片】**/
        fun requestAddImg(arrayList: ArrayList<UserData> , strSelect : String)

        /**【响应添加图片】**/
        fun responseAddImg(boolean: Boolean , arrayLists: ArrayList<UserData>)

        /**【请求图片点击事件】**/
        fun requestOnClick(context: Context , user: UserData)

        /**【请求图片长按事件】**/
        fun requestOnLongClick(context: Context , user: UserData)

        /**【响应图片长按事件】**/
        fun responseOnLongClick(intent: Intent)

        /**【请求拍照返回数据处理】**/
        fun requestPhotoData(fileName : String , context: Context)
    }

}