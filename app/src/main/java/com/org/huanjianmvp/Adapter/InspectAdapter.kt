package com.org.huanjianmvp.Adapter

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.org.huanjianmvp.Domain.inspectCheck
import com.org.huanjianmvp.R
import com.org.huanjianmvp.Utils.StringUtils
import kotlinx.android.synthetic.main.inspect_item.view.*
import java.util.HashMap

/**
 * 【外检检验项适配器】
 * Created by Administrator on 2020/8/25.
 */
class InspectAdapter(list: ArrayList<inspectCheck>) : RecyclerView.Adapter<InspectAdapter.inspectHolder>() {

    var listData = ArrayList<inspectCheck>()                //TextView初始赋值数据
    var spinnerMap = HashMap<String , String>()             //每次改变在监听中更新数据

    init {
        listData = list
        for (i in listData.indices){            // i 从 0 开始
            var mayKey = StringUtils.inspectMapKey(listData[i].title)
            spinnerMap.put(mayKey,"1")
            Log.e("【初始中的值】",spinnerMap.get(mayKey)+"\t\t【map的key值】\t\t"+mayKey+"\t\ti的值为:"+i)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): inspectHolder {
        var view = LayoutInflater.from(parent?.context).inflate(R.layout.inspect_item,parent,false)
        var holder = inspectHolder(view)
        return holder
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: inspectHolder?, position: Int) {
        //holder?.textView?.text = (position+1).toString()+ "." + listData[position]
        holder?.textView?.text = (position + 1).toString() + "." + listData[position].title     //设置标题内容
        if (listData[position].isCheck){
            holder?.textResult?.text = "是"
            //holder?.textResult?.setTextColor(Color.GREEN)
        }else{
            holder?.textResult?.text = "否"
            //holder?.textResult?.setTextColor(Color.RED)
        }

        holder?.textResult?.setOnClickListener {

            var mapKey = StringUtils.inspectMapKey(listData[position].title)
            Log.e("position的值为", position.toString() +"\tmap的键值为:\t" + mapKey)
            /**【将每一个spinner的选中值都放到map中储存起来、改变的时候更新map中的数据】**/
            if (listData[position].isCheck){
                holder?.textResult?.text = "否"
                //holder?.textResult?.setTextColor(Color.RED)
                listData[position].isCheck = false
                spinnerMap.put(mapKey ,"0")
            }else{
                holder?.textResult?.text = "是"
                //holder?.textResult?.setTextColor(Color.GREEN)
                listData[position].isCheck = true
                spinnerMap.put(mapKey ,"1")
            }

        }
    }



    class inspectHolder(view : View) : RecyclerView.ViewHolder(view){
        var textView = view.inspect_Text
        var textResult = view.textResult
    }

}