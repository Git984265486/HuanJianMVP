package com.org.huanjianmvp.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.org.huanjianmvp.Domain.inspectCheck
import com.org.huanjianmvp.R

/**
 * 安检中复检登陆的适配器
 * Created by Administrator on 2020/9/18.
 */
class AJRecheckLoginAdapter(listData: ArrayList<inspectCheck> , context: Context) : RecyclerView.Adapter<AJRecheckLoginAdapter.holderView>() {

    var list = ArrayList<inspectCheck>()
    var holder: holderView? = null
    var mContext: Context? = null
    var listRecheck : ArrayList<String> = ArrayList()    //每次改变在监听中更新数据

    init {
        this.list = listData
        this.mContext = context
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): holderView {
        var view: View = LayoutInflater.from(parent!!.context).inflate(R.layout.aj_recheck_login_item,parent,false)
        holder = holderView(view)
        return holder as holderView
    }

    override fun onBindViewHolder(holder: holderView?, position: Int) {
        holder!!.textView.text = list[position].title
        if (list[position].isCheck){
            holder.checkBox.setBackgroundDrawable(mContext!!.resources.getDrawable(R.drawable.checkbox_null))
        }else{
            holder.checkBox.setBackgroundDrawable(mContext!!.resources.getDrawable(R.drawable.checkbox))
        }
        holder.checkBox.setOnClickListener {
            if (list[position].isCheck){
                holder.checkBox.setBackgroundDrawable(mContext!!.resources.getDrawable(R.drawable.checkbox))
                list[position].isCheck = false
                addListValue(list[position].title)   //添加数据到ArrayList中
            }else{
                holder.checkBox.setBackgroundDrawable(mContext!!.resources.getDrawable(R.drawable.checkbox_null))
                list[position].isCheck = true
                removeListData(list[position].title) //移除ArrayList中的数据
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    /**【添加数据到map中】**/
    fun addListValue(value: String): String{
        var arrayList = value.split("-")
        listRecheck.add(arrayList[0])
        //listArrayListContent(listRecheck)
        return arrayList[0]
    }
    /**【移除map中的数据】**/
    fun removeListData(value: String){
        var arrayList = value.split("-")
        listRecheck.remove(arrayList[0])
        //listArrayListContent(listRecheck)
    }

    /**【遍历ArrayList中的内容】**/
    fun listArrayListContent(list: ArrayList<String>){
        for (i in list.indices){
            Log.e("【ArrayList中的内容】" , list[i] + "\t...")
        }
    }

    /**【展示内容】**/
    class holderView(view: View): RecyclerView.ViewHolder(view){
        var checkBox: TextView = view.findViewById(R.id.recheckLoginImg)
        var textView: TextView = view.findViewById(R.id.recheckLoginText)
    }

}