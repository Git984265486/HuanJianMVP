package com.org.huanjianmvp.Model

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.StrictMode
import android.provider.MediaStore
import android.util.Log
import com.org.huanjianmvp.Activity.ImageShow
import com.org.huanjianmvp.Base.BaseFragmentModel
import com.org.huanjianmvp.Contrast.PhotographContrast
import com.org.huanjianmvp.Domain.UserData
import com.org.huanjianmvp.Presenter.PhotographFragmentPresenter
import com.org.huanjianmvp.Utils.FileUtils
import com.org.huanjianmvp.Utils.SQLiteFunction
import com.org.huanjianmvp.Utils.StaticValues
import java.io.File
import java.io.FileInputStream

/**
 * Created by Administrator on 2020/8/24.
 */
class PhotographFragmentModel(mPresenter: PhotographFragmentPresenter?):
        BaseFragmentModel<PhotographFragmentPresenter,PhotographContrast.Model>(mPresenter) {
    override fun getContract(): PhotographContrast.Model {
        return object : PhotographContrast.Model{
            override fun actionPhotoData(fileName : String , context: Context) {
                var inputStream: FileInputStream? = null
                try {
                    inputStream = FileInputStream(FileUtils.createFileFolder() +  fileName)
                    var bitmap = BitmapFactory.decodeStream(inputStream)                //获取指定目录下保存的位图
                    /**【数据保存到数据库中】**/
                    var lite = SQLiteFunction(context, StaticValues.SQLiteName,null, StaticValues.SQLiteVersion)
                    lite.insertData(fileName,"0",bitmap)        //保存图片名字、上传状态、位图到SQLite中
                    var waterMark = FileUtils.bitmapWaterMark(bitmap)                   //给位图添加水印
                    FileUtils.overWriteFile(waterMark , FileUtils.createFileFolder()+fileName)  //水印位图覆盖原来的图片
                }catch ( ex : Exception){
                    ex.printStackTrace()
                    presenter.weakView.get()!!.responseError(ex.message , ex)
                }finally {
                    inputStream?.close()
                }
            }

            /**【图片长按处理】**/
            override fun actionOnLongClick(context: Context, user: UserData) {
                var intentPhoto = Intent("android.media.action.IMAGE_CAPTURE")
                var fileName = user.age + "_" + user.userName + ".jpg"      //文件名字【流水号 + 照片名】
                Log.e("图片名字","\t\t" + fileName)
                var file = File(FileUtils.createFileFolder() + fileName)//新建一个文件
                var uri = Uri.fromFile(file) as Uri                     //将File文件转换成Uri以启动相机程序
                intentPhoto.putExtra(MediaStore.EXTRA_OUTPUT , uri)     //指定图片输出地址
                presenter.contract.responseOnLongClick(intentPhoto)     //返回Intent
            }

            /**【图片点击处理】**/
            override fun actionOnClick(context: Context, user: UserData) {
                var fileName = user.age + "_" + user.userName + ".jpg"  //文件名字【流水号 + 照片名】
                if (FileUtils.fileIsExists(fileName)) {              //判断目录下是否存在文件
                    var intent = Intent(context , ImageShow::class.java)
                    intent.putExtra("fileName",fileName)    //传递文件名字
                    context.startActivity(intent)
                }
            }

            /**【获取数据】**/
            override fun actionGetData(user : UserData) {

                /**
                 * android.os.FileUriExposedException: file:///storage/emulated/0/ilive/images/photophoto.jpeg
                 * exposed beyond app through ClipData.Item.getUri()
                 * 下面三行解决此处问题
                 * **/
                var builder = StrictMode.VmPolicy.Builder()
                StrictMode.setVmPolicy(builder.build())
                builder.detectFileUriExposure()


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

                var arrayListSpinner = ArrayList<String>()
                arrayListSpinner.add("车辆VIN照")
                arrayListSpinner.add("车辆排气照")
                arrayListSpinner.add("燃油蒸发控制系统照")
                arrayListSpinner.add("污染控制装置照")

                presenter.contract.responseData(arrayList , arrayListSpinner)

            }

            /**【图片添加处理】**/
            override fun actionAddImage(arrayLists: ArrayList<UserData>, strSelect: String) {
                var isHave = false
                var user = UserData()
                for (i in arrayLists.indices) {
                    if (strSelect == arrayLists[i].userName) {
                        isHave = true
                        break
                    }
                }
                if (!isHave){
                    user.age = arrayLists[0].age
                    user.userName = strSelect
                    arrayLists.add(user)
                    presenter.contract.responseAddImg(isHave,arrayLists)
                }
            }

        }
    }
}