package com.org.huanjianmvp.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 文件处理工具类
 * Created by Administrator on 2020/9/3.
 */

public class FileUtils {


    //图片储存文件夹、不存在则创建一个新的文件目录
    public static String createFileFolder(){
        String filePath = Environment.getExternalStorageDirectory().toString() + "/AppNewFolder/";
        File fileFolder = new File(filePath);
        if (!fileFolder.exists()){
            fileFolder.mkdirs();
        }
        return filePath;
    }

    //判断文件是否存在
    public static boolean fileIsExists(String filePath){
        String path = Environment.getExternalStorageDirectory().toString() + "/AppNewFolder/";
        File file = new File(path + filePath);
        if (file.exists()){
            return true;
        }
        return false;
    }

    //覆盖目录下指定文件          Bitmap:要覆盖的内容   fileName:被覆盖的文件、若是图片无任何操作会覆盖失败
    public static boolean overWriteFile(Bitmap bitmap , String fileName){
        boolean result = false;                 //默认结果
        if (bitmap != null){
            OutputStream outputStream = null;   //文件输出流
            try {
                File file = new File(fileName);
                outputStream = new FileOutputStream(file);
                //将图片压缩为JPEG格式写到文件输出流，100是最大的质量程度
                result = bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if (outputStream != null){
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return result;
    }

    //压缩获取图片内容
    public static String getPhotoData(String fileName){
        File file = new File(fileName);
        String photo = null;
        if (!file.exists()){            //如果图片不存在
            return photo;
        }
        Bitmap bitmap = BitmapFactory.decodeFile(fileName);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
        byte [] bytes = outputStream.toByteArray();
        photo = Base64.encodeToString(bytes,Base64.DEFAULT);
        return photo;
    }


    /** 【储存图片到数据库中时】将bitmap转化成byte[]  **/
    public static byte[] bitmapToByte(Bitmap bitmap){
        byte[] photoData = null;
        if (bitmap != null){
            int size = bitmap.getWidth() * bitmap.getHeight() * 4;          //创建一个字节数组输出流,流的大小为size
            ByteArrayOutputStream baos = new ByteArrayOutputStream(size);   //设置位图的压缩格式，质量为100%，并放入字节数组输出流中
            bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);    //将字节数组输出流转化为字节数组
            photoData = baos.toByteArray();
            Log.e("转化成byte的bitmap的长度","\t\t" + photoData.length);
        }
        return photoData;
    }

    /** 【从数据库中拿出图片时】将byte[]转化成bitmap  **/
    public static Bitmap byteToBitmap(byte[] photo){
        Bitmap bitmap = null;
        if (photo.length != 0){
            bitmap = BitmapFactory.decodeByteArray(photo,0,photo.length);
            Log.e("转化成bitmap的byte的长度","\t\t" + photo.length);
        }
        return bitmap;
    }

    /**     【bitmap 转 Drawable】  **/
    public static Drawable bitmapToDrawable(Bitmap bitmap){
        Drawable drawable = null;
        if (bitmap != null){
            drawable = new BitmapDrawable(bitmap);
        }
        return drawable;
    }

    /**【Bitmap 旋转，每次旋转度数：rotate * 90 】**/
    public static Bitmap bitmapRotate(Bitmap bitmap , int rotate){
        Matrix matrix = new Matrix();
        // 设置旋转角度
        matrix.setRotate(rotate * 90);
        // 重新绘制Bitmap
        bitmap = Bitmap.createBitmap(bitmap, bitmap.getWidth()/10, 0, bitmap.getWidth()*7/8,bitmap.getHeight(), matrix, true);
        return bitmap;
    }

    /**【Bitmap 位图添加水印】**/
    public static Bitmap bitmapWaterMark(Bitmap bitmap){
        if (bitmap != null){
            String time = "时间戳:" + System.currentTimeMillis();   //水印内容
            int width = bitmap.getWidth();                          //长
            int height = bitmap.getHeight();                        //高
            Bitmap icon = Bitmap.createBitmap(width , height , Bitmap.Config.ARGB_8888);//建立一个空的Bitmap
            Canvas canvas = new Canvas(icon);                       //初始化画布到绘制的图像icon上
            Paint paint = new Paint();                              //建立画笔
            paint.setDither(true);                                  //获取清晰的图像采样
            paint.setFilterBitmap(true);                            //过滤一些
            Rect src = new Rect(0 , 0 , width , height);  //创建一个指定的新矩形坐标
            Rect dst = new Rect(0 , 0 , width , height);  //创建一个指定的新矩形坐标
            canvas.drawBitmap(bitmap , src , dst , paint);          //将bitmap缩小或者扩大到 dst使用的填充区paint
            Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DEV_KERN_TEXT_FLAG);
            textPaint.setTextSize(60.0f);                           //画笔字体大小
            textPaint.setTypeface(Typeface.DEFAULT);                //采用默认的宽度DEFAULT_BOLD
            textPaint.setColor(Color.RED);                          //采用的颜色
            canvas.drawText(time,10,50,textPaint);            //添加水印文字
            canvas.save(Canvas.ALL_SAVE_FLAG);                      //保存canvas
            canvas.restore();                                        //储存
            return icon;
        }
        return null;
    }
}
