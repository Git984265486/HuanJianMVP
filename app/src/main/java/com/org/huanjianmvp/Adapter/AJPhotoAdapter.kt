package com.org.huanjianmvp.Adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.org.huanjianmvp.Domain.UserData
import com.org.huanjianmvp.R
import com.org.huanjianmvp.Utils.FileUtils
import com.org.huanjianmvp.Utils.SQLiteFunction
import com.org.huanjianmvp.Utils.StaticValues
import kotlinx.android.synthetic.main.take_photo_item.view.*

/**
 * 安检拍照适配器
 * Created by Administrator on 2020/9/22.
 */
class AJPhotoAdapter(list: List<UserData>, recyView: RecyclerView, context: Context) : RecyclerView.Adapter<AJPhotoAdapter.photoShow>(),
        View.OnClickListener,View.OnLongClickListener{

    private var photoClickListener: PhotoClickListener? = null          //声明一下点击接口
    private var photoLongClickListener: PhotoLongClickListener? = null  //声明一下长按接口
    private var recyclerView: RecyclerView? = null
    private var dataList : List<UserData>? = null
    private var mContext : Context? = null

    init {
        this.recyclerView = recyView
        this.dataList = list
        this.mContext = context
    }

    /**【图片点击监听】**/
    fun setOnPhotoClickListener(photoClickListener: PhotoClickListener) {
        this.photoClickListener = photoClickListener
    }

    /**【图片长按监听】**/
    fun setOnPhotoLongClickListener(photoLongClickListener: PhotoLongClickListener){
        this.photoLongClickListener = photoLongClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): AJPhotoAdapter.photoShow {
        var view = LayoutInflater.from(parent?.context).inflate(R.layout.take_photo_item,parent,false)
        view.setOnClickListener(this)       //设置点击监听器
        view.setOnLongClickListener(this)   //设置长按监听器
        var holder = AJPhotoAdapter.photoShow(view)
        return holder
    }

    override fun onBindViewHolder(holder: AJPhotoAdapter.photoShow?, position: Int) {
        var fileName = dataList!![position].age + "_" + dataList!![position].userName + ".jpg"     //图片名字
        var lite = SQLiteFunction(mContext, StaticValues.SQLiteName,null, StaticValues.SQLiteVersion)

        /**【图片说明】**/
        holder!!.textView.text = dataList!![position].userName

        /**【图片展示】**/
        if (FileUtils.fileIsExists(fileName)){          //判断文件是否存在、true:存在  false:不存在
            var filePath = FileUtils.createFileFolder() + fileName  //获取目录下的图片
            var imgBitmap = BitmapFactory.decodeFile(filePath,null)
            Log.e("展示图片来源","\t\t手机文件夹\t\t" + position.toString())
            holder!!.imageView.setImageBitmap(imgBitmap)
        }else{
            var bitmap = lite.queryData(fileName)
            Log.e("展示图片来源","\t\tSQLite数据库\t\t" + position.toString())
            holder!!.imageView.setImageBitmap(bitmap)
        }

        /**【图片状态】**/
        if(lite.queryUploadStatus(fileName) == "1"){
            holder!!.uploadText.text = "已上传"
        }else{
            holder!!.uploadText.text = "未上传"
        }
    }

    override fun getItemCount(): Int {
        return dataList!!.size
    }


    /**【定义一个内部类，展示的数据由内部类决定】 */
    class photoShow(view: View) : RecyclerView.ViewHolder(view){
        var imageView: ImageView = view.takePhotoImg    //图片
        var textView: TextView = view.takePhotoText     //图片说明
        var uploadText : TextView = view.uploadText     //图片上传状态
    }

    /**【点击事件】**/
    override fun onClick(view: View?) {
        //根据RecyclerView获得当前View的位置
        var position: Int= recyclerView?.getChildAdapterPosition(view)!!
        //程序执行到此，会去执行具体实现的onItemClick()方法
        if (photoClickListener != null) {
            photoClickListener?.onPhotoClick(recyclerView,view,position,dataList!![position])
        }
    }

    /**【长按事件】**/
    override fun onLongClick(view: View?): Boolean {
        //根据RecyclerView获得当前View的位置
        var position: Int= recyclerView?.getChildAdapterPosition(view)!!
        //程序执行到此，会去执行具体实现的photoLongClickListener()方法
        if (photoLongClickListener!=null){
            photoLongClickListener?.onPhotoLongClick(recyclerView,view,position,dataList!![position])
        }
        return false
    }

}