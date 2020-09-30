package com.org.huanjianmvp.Activity

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.View
import com.org.huanjianmvp.Base.BaseActivity
import com.org.huanjianmvp.Contrast.ImageShowContrast
import com.org.huanjianmvp.Presenter.ImageShowPresenter
import com.org.huanjianmvp.R
import com.org.huanjianmvp.Utils.FileUtils
import com.org.huanjianmvp.Utils.SQLiteFunction
import com.org.huanjianmvp.Utils.StaticValues
import kotlinx.android.synthetic.main.image_show.*

/**
 * 图片展示页面
 * **/

class ImageShow : BaseActivity<ImageShowPresenter,ImageShowContrast.ViewAndPresenter>() {

    private var bitmap: Bitmap? = null          //原图
    private var finalBitmap : Bitmap? = null    //最终效果图
    private var sqlBitmap : Bitmap? = null      //数据库位图更新
    private var rotateCount = 1                 //位图旋转次数
    private var fileName : String? = null       //图片名字

    override fun getContract(): ImageShowContrast.ViewAndPresenter {
        return object : ImageShowContrast.ViewAndPresenter{

        }
    }

    override fun createPresenter(): ImageShowPresenter {
        return ImageShowPresenter()
    }

    override fun getViewID(): Int {
        return R.layout.image_show
    }

    override fun initViewUI() {
        application.addActivity(this@ImageShow)     //将本页面添加到页面统一管理中

        fileName = intent.getStringExtra("fileName")        //拿到图片名字
        var lite = SQLiteFunction(this@ImageShow,StaticValues.SQLiteName,null,StaticValues.SQLiteVersion)
        bitmap = lite.queryData(fileName)                           //通过文件名字查找数据库中的bitmap
        sqlBitmap = bitmap                                          //初始默认数据库bitmap
        //bitmap = BitmapFactory.decodeFile(fileName,null)          //通过路径获取位图
        photoView.enable()              //启用图片缩放
        photoView.maxScale = 3.0f       //图片最大缩放倍数
        photoView.setImageBitmap(FileUtils.bitmapWaterMark(bitmap)) //位图展示

        //var photoStr = FileUtils.getPhotoData(FileUtils.createFileFolder()+fileName)    //图片压缩转成String
        //Log.e("图片信息","\t\t" + photoStr)
    }

    override fun initListener() {
        btnDelete.setOnClickListener(this@ImageShow)
        btnRotate.setOnClickListener(this@ImageShow)
        btnSave.setOnClickListener(this@ImageShow)
    }

    override fun onClick(view: View?) {
        when(view!!.id){
            R.id.btnRotate->{
                var bitmapRotate = FileUtils.bitmapRotate(bitmap , rotateCount)     //位图旋转
                sqlBitmap = bitmapRotate                                            //旋转过后的数据库位图
                rotateCount ++                                                      //旋转次数，每点击一次：顺时针旋转 90°* 次数
                finalBitmap = FileUtils.bitmapWaterMark(bitmapRotate)               //给位图添加水印
                photoView.setImageBitmap(finalBitmap)                               //更新展示位图
            }
            R.id.btnDelete->{
                Log.e("删除按钮","删除图片")
                this@ImageShow.finish()
            }
            R.id.btnSave->{
                var lite = SQLiteFunction(this@ImageShow,StaticValues.SQLiteName,null,StaticValues.SQLiteVersion)
                lite.updateData(fileName,sqlBitmap)               //更新数据库中保存的bitmap
                var filePath = FileUtils.createFileFolder() + fileName      //文件保存路径及名字
                var isSuccess = FileUtils.overWriteFile(finalBitmap , filePath)//要保存的位图：finalBitmap（数据库也要更新）
                if (isSuccess){
                    Log.e("保存按钮","图片覆盖成功！")
                }else{
                    Log.e("保存按钮","图片覆盖失败！")
                }
                this@ImageShow.finish()
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
