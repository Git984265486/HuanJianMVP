package com.org.huanjianmvp.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.util.Log;

/**
 * 创建SQLite数据库储存图片
 * Created by Administrator on 2020/9/3.
 */

public class SQLiteFunction extends SQLiteOpenHelper{

    private static final String TABLE_NAME = "TabPhoto";                    //表格名称
    private static final String TABLE_CREATE = "create table if not exists  TabPhoto("     //表格创建语句
            +"[id] INTEGER PRIMARY KEY AUTOINCREMENT,"                      //主键自增
            +"[name] TEXT,"                                                 //名字
            +"[time] TEXT,"                                                 //录入时间
            +"[photo] BINARY,"                                              //照片保存为binary格式
            +"[status] TEXT)";                                              //照片上传状态

    public SQLiteFunction(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CREATE);               //创建表格
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        //sqLiteDatabase.execSQL("drop table if exists ImageTab");
    }

    //调用onCreate创建表格
    public void create(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        onCreate(sqLiteDatabase);
    }

    //插入数据
    public void insertData(String name , String status , Bitmap bitmap){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("status",status);
        values.put("time",TimeUtils.getNowDay());
        values.put("photo",FileUtils.bitmapToByte(bitmap));               //将字节数组保存到数据库中
        //查找数据库中是否存在字段 [ name = name ] 的数据
        Cursor cursor = database.query(TABLE_NAME,null,"name=?",new String[]{ name },null,null,null);
        if (cursor.getCount() != 0){
            //存在则更新数据
            database.update(TABLE_NAME,values,"name=?",new String[]{ name } );
        }else{
            //不存在则新增数据
            database.insert(TABLE_NAME,null,values);
        }
    }

    //查找返回bitmap
    public Bitmap queryData(String queryName){
        Bitmap bitmap = null;
        if (queryName!=null){
            SQLiteDatabase database = this.getWritableDatabase();
            Cursor cursor = database.query(TABLE_NAME,null,"name=?",new String[]{ queryName },null,null,null);
            if (cursor.getCount() != 0){
                cursor.moveToFirst();
                byte[] photo = cursor.getBlob(cursor.getColumnIndex("photo"));
                Log.e("SQLite查找bitmap的byte长度","\t\t" + photo.length);
                bitmap = FileUtils.byteToBitmap(photo);
            }
        }
        return bitmap;
    }

    //查找返回照片上传状态    status
    public String queryUploadStatus(String queryName){
        String uploadStatus = "";
        if (queryName!=null){
            SQLiteDatabase database = this.getWritableDatabase();
            Cursor cursor = database.query(TABLE_NAME,null,"name=?",new String[]{ queryName },null,null,null);
            if (cursor.getCount() != 0){
                cursor.moveToFirst();
                uploadStatus = cursor.getString(cursor.getColumnIndex("status"));
                Log.e("查找数据库上传状态","\t\t" + uploadStatus);
            }
        }
        return uploadStatus;
    }

    //通过字段：name 更新字段：time 、  bitmap
    public void updateData(String name , Bitmap bitmap){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("time",TimeUtils.getNowDay());
        values.put("photo",FileUtils.bitmapToByte(bitmap));
        int result = database.update(TABLE_NAME,values,"name=?",new String[]{ name } );
        Log.e("Bitmap数据更新结果","\t\t" + result);
    }

    //通过字段：name 更新字段：time 、 status
    public void updateStatus(String name ,String status){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("status",status);
        values.put("time",TimeUtils.getNowDay());
        int result = database.update(TABLE_NAME,values,"name=?",new String[]{ name } );
        Log.e("Status数据更新结果","\t\t" + result);
    }


    //遍历所有数据、删除3天前的数据
    public void delDataForThreeDay() throws Exception {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.query(TABLE_NAME,new String[]{"name","time"},null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String time = cursor.getString(cursor.getColumnIndex("time"));
                if (TimeUtils.dayDiff(time) >= 3){                 //当前时间和录入时间作比较
                    int result = database.delete(TABLE_NAME,"name=?",new String[]{ name });     //删除数据
                    Log.e("数据删除结果\t",result + "...");
                }
            }while (cursor.moveToNext());
        }
    }

    //后台打印全部数据
    public void LogData(){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.query(TABLE_NAME,null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                String name = "\t\t数据库中name:\t" + cursor.getString(cursor.getColumnIndex("name"));
                String status = "\t\t数据库中status:\t" + cursor.getString(cursor.getColumnIndex("status"));
                String time = "\t\t数据库中time:\t" + cursor.getString(cursor.getColumnIndex("time"));
                Log.e("数据库中ID",cursor.getInt(0) + "\t" + name + status + time);
                //Log.e("数据库中name",cursor.getString(cursor.getColumnIndex("name")));
                //Log.e("数据库中status",cursor.getString(cursor.getColumnIndex("status")));
                //Log.e("数据库中time",cursor.getString(cursor.getColumnIndex("time")));
            }while (cursor.moveToNext());
        }
    }

}
