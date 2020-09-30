package com.org.huanjianmvp.Activity

import android.content.Intent
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import com.org.huanjianmvp.Base.BaseActivity
import com.org.huanjianmvp.Contrast.FunctionContrast
import com.org.huanjianmvp.Domain.UserData
import com.org.huanjianmvp.Presenter.FunctionActivityPresenter
import com.org.huanjianmvp.R
import com.org.huanjianmvp.Utils.FileUtils
import com.org.huanjianmvp.Utils.SQLiteFunction
import com.org.huanjianmvp.Utils.StaticValues
import kotlinx.android.synthetic.main.function.*

/**
 * 功能页面
 * **/

class Function : BaseActivity<FunctionActivityPresenter,FunctionContrast.ViewAndPresenter>() {

    var user : UserData? = null
    var bundle: Bundle? = null

    override fun getContract(): FunctionContrast.ViewAndPresenter {
        return object : FunctionContrast.ViewAndPresenter{

        }
    }

    override fun createPresenter(): FunctionActivityPresenter {
        return FunctionActivityPresenter()
    }

    override fun getViewID(): Int {
        return R.layout.function
    }

    override fun initViewUI() {
        application.addActivity(this@Function)
        user = intent.getSerializableExtra("data") as UserData

        funCarNumType.text = user?.sex
        funSerialNumber.text = user?.userName
        funCarType.text = user?.age
        funCarNumber.text = user?.userName
        user?.ShowLog()
        Log.e("功能页面打印数据","【完毕】")
    }

    override fun initListener() {
        save.setOnClickListener(this)
        test.setOnClickListener(this)
        testBtn1.setOnClickListener(this)
        testBtn2.setOnClickListener(this)
        testBtn3.setOnClickListener(this)
        testBtn4.setOnClickListener(this)

        drawerMenu.setNavigationItemSelectedListener ({item->
            when(item.itemId){
                R.id.uploadStatus->{
                    Log.e("侧滑菜单","照片上传状态")
                    var uploadStatus = Intent(this@Function,MenuSetting::class.java)
                    var bundle = Bundle()
                    bundle.putSerializable("data",user)
                    uploadStatus.putExtras(bundle)
                    startActivity(uploadStatus)
                }
                R.id.baseSetting->{
                    Log.e("侧滑菜单","基本参数设置")
                    var intentSetting = Intent(this@Function,MenuSetting::class.java)
                    var bundle = Bundle()
                    bundle.putSerializable("data",user)
                    intentSetting.putExtras(bundle)
                    startActivity(intentSetting)
                }
                R.id.updatePassword->{
                    Log.e("侧滑菜单","密码更改")
                    var updatePassword = Intent(this@Function,MenuSetting::class.java)
                    var bundle = Bundle()
                    bundle.putSerializable("data",user)
                    updatePassword.putExtras(bundle)
                    startActivity(updatePassword)
                }
            }
            false
        })
    }

    override fun onClick(view: View?) {
        when(view!!.id){
            R.id.save ->{
                var intent = Intent(this@Function,InspectionAndPhotograph::class.java)
                //bundle!!.putSerializable("data",user)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            R.id.test->{
                var lite = SQLiteFunction(this@Function,StaticValues.SQLiteName,null,StaticValues.SQLiteVersion)
                lite.create()                                       //创建表格
                //var bitmap = BitmapFactory.decodeResource(resources,R.drawable.icon,null)
                //lite.insertData("admin","0",bitmap)               //插入数据到SQLite数据库
                //lite.LogData()
                var bitmap = lite.queryData("admin")    //通过name字段查找返回bitmap
                //lite.updateData("1","admin" , bitmap)          //通过name字段更新status
                //Log.e("bitmap的长度",bitmap.width.toString())
                test.background = FileUtils.bitmapToDrawable(bitmap)
            }

            R.id.testBtn1->{
                var AJCheck = Intent(this@Function,AJCheckAndPhotoAndBaseInfo::class.java)
                AJCheck.putExtras(bundle)
                startActivity(AJCheck)
            }

            R.id.testBtn2->{
                var AJPhotoIntent = Intent(this@Function,AJVehicleListAndInitLoginAndRecheckLogin::class.java)
                AJPhotoIntent.putExtras(bundle)
                startActivity(AJPhotoIntent)
            }

            R.id.testBtn3->{
                var chassisIntent = Intent(this@Function , Chassis::class.java)
                startActivity(chassisIntent)
            }

            R.id.testBtn4->{

            }
        }
    }

    override fun destroy() {
        this@Function.finish()
    }

    override fun initDatas() {
        bundle = Bundle()
        bundle!!.putSerializable("data",user)
    }

    override fun <ERROR : Any?> responseError(error: ERROR, throwable: Throwable?) {

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        this@Function.finish()
        return super.onKeyDown(keyCode, event)
    }

}
