package com.org.huanjianmvp.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.org.huanjianmvp.R
import com.org.huanjianmvp.Utils.StaticValues
import kotlinx.android.synthetic.main.aj_baseinfo_item.view.*

/**
 * 安检基本信息适配器
 * Created by Administrator on 2020/9/11.
 */
class AJBaseInfoAdapter(listData: ArrayList<String>) : RecyclerView.Adapter<AJBaseInfoAdapter.viewHolder>(){

    var list = ArrayList<String>()
    var holder: viewHolder? = null

    init {
        this.list = listData
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): viewHolder {
        var view : View = LayoutInflater.from(parent!!.context).inflate(R.layout.aj_baseinfo_item,parent,false)
        holder = viewHolder(view)
        return holder as viewHolder
    }

    override fun onBindViewHolder(holder: viewHolder?, position: Int) {
        holder!!.textTitle.text = StaticValues.AJBaseInfoTitle[position]
        holder!!.textText.text = list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }


    /**【内部类，展示的内容】**/
    class viewHolder(view : View): RecyclerView.ViewHolder(view){
        var textTitle : TextView = view.baseTitle
        var textText : TextView = view.baseText
    }

}