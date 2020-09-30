package com.org.huanjianmvp.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.org.huanjianmvp.Domain.UserData;
import com.org.huanjianmvp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 展示数据信息，登录进来后进行展示，数据展示页面适配器
 * Created by Administrator on 2020/8/19.
 */

public class ListDatasAdapter extends RecyclerView.Adapter<ListDatasAdapter.viewHolder> implements View.OnClickListener{

    private List<UserData> dataList = new ArrayList<>();

    private RecyclerView rv;
    private ItemClickListener onItemClickListener;   //声明一下这个接口
    //提供setter方法
    public void setOnItemClickListener(ItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }


    /**【定义一个内部类，展示的数据由内部类决定】**/
    static class viewHolder extends RecyclerView.ViewHolder{

        private TextView listSerialNumber , listCarNumber , listCarNumType ,listCarID;

        public viewHolder(View view) {
            super(view);
            listSerialNumber = view.findViewById(R.id.listSerialNumber);
            listCarNumber = view.findViewById(R.id.listCarNumber);
            listCarNumType = view.findViewById(R.id.listCarNumType);
            listCarID = view.findViewById(R.id.listCarID);
        }
    }

    /**【构造函数初始化数据】**/
    public ListDatasAdapter(List<UserData> list , RecyclerView recyclerView){
        dataList = list;
        rv = recyclerView;
    }

    /**【子项布局文件获取】**/
    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_datas_item,parent,false);
        view.setOnClickListener(this);      //设置监听器
        final viewHolder holder = new viewHolder(view);
        return holder;
    }

    /**【给子项赋值】**/
    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        UserData users = dataList.get(position);
        holder.listSerialNumber.setText(users.getUserName());
        holder.listCarNumber.setText(users.getAge());
        holder.listCarNumType.setText(users.getSex());
        holder.listCarID.setText(users.getUserName());
    }

    /**【返回子项总数量】**/
    @Override
    public int getItemCount() {
        return dataList.size();
    }


    @Override
    public void onClick(View view) {
        //根据RecyclerView获得当前View的位置
        int position = rv.getChildAdapterPosition(view);
        //程序执行到此，会去执行具体实现的onItemClick()方法
        if (onItemClickListener!=null){
            onItemClickListener.onItemClick(rv,view,position,dataList.get(position));
        }
    }

}
