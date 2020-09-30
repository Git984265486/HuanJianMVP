package com.org.huanjianmvp.Adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.org.huanjianmvp.Domain.inspectCheck
import com.org.huanjianmvp.R
import kotlinx.android.synthetic.main.ajcheck_item.view.*
import java.util.HashMap

/**
 * 安检外检检验项 适配器
 * Created by Administrator on 2020/9/4.
 */
class AJCheckAdapter(listData: ArrayList<inspectCheck>) : RecyclerView.Adapter<AJCheckAdapter.myViewHolder>(){

    var spinnerMap = HashMap<String , String>()     //每个spinner默认选择数据、每次改变在监听中更新数据
    var viewHolder: myViewHolder? = null            //子项的布局
    var list: ArrayList<inspectCheck>? = null


    init {
        this.list = listData
        for (n in 0..(list!!.size -1)){
            spinnerMap!!.put(n.toString(),"1")
            Log.e("【初始中的值】",spinnerMap.get(n.toString())+"\t\t【初始的长度】\t\t"+spinnerMap.size.toString()+"\t\tn的值为:"+n)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): myViewHolder {
        var view : View = LayoutInflater.from(parent?.context).inflate(R.layout.ajcheck_item,parent,false)
        viewHolder = myViewHolder(view)
        return viewHolder as myViewHolder
    }

    override fun onBindViewHolder(holder: myViewHolder?, position: Int) {
        if (list!![position].isCheck){
            holder!!.textResult.text = "合格"
            //holder!!.textResult.setTextColor(Color.GREEN)
        }else{
            holder!!.textResult.text = "不合格"
            //holder!!.textResult.setTextColor(Color.RED)
        }
        holder!!.textTitle.text = list!![position].title

        /**【设置监听】**/
        holder!!.textResult.setOnClickListener {
            if (list!![position].isCheck){
                holder!!.textResult.text = "不合格"
                //holder!!.textResult.setTextColor(Color.RED)
                list!![position].isCheck = false
                spinnerMap!!.put(position.toString(),"0")
            }else{
                holder!!.textResult.text = "合格"
                //holder!!.textResult.setTextColor(Color.GREEN)
                list!![position].isCheck = true
                spinnerMap!!.put(position.toString(),"1")
            }
        }

    }

    /**【总数】**/
    override fun getItemCount(): Int {
        return this.list!!.size
    }


    /**【子项的视图】**/
    class myViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textTitle: TextView = view.check_items
        var textResult: TextView = view.checkResult
    }


}