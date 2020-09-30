package com.org.huanjianmvp.Utils;

import java.util.ArrayList;

/**
 * Created by Administrator on 2020/9/9.
 */

public class StringUtils {

    /**【 返回【外检】中储存到  map  中的  key  的值 】**/
    public static String inspectMapKey(String itemName){
        String mapKey = null;
            if (itemName.equals("车辆机械状况是否良好")){
                mapKey = "jxzksflh";
                return mapKey;
            }else if (itemName.equals("排气污染控制装置是否齐全，正常")){
                mapKey = "pqwrkzzzsfqq";
                return mapKey;
            }else if (itemName.equals("车辆是否存在烧机油或者严重冒黑烟现象")){
                mapKey = "sfmhy";
                return mapKey;
            }else if (itemName.equals("(汽油车)燃油蒸发控制系统是否正常")){
                mapKey = "ryzfxt";
                return mapKey;
            }else if (itemName.equals("车上仪表工作是否正常")){
                mapKey = "csybgz";
                return mapKey;
            }else if (itemName.equals("有无可能影响安全或引起测试偏差的机械故障")){
                mapKey = "cspcdjxgz";
                return mapKey;
            }else if (itemName.equals("车辆进、排气系统是否有任何泄露")){
                mapKey = "jpqxtsfxl";
                return mapKey;
            }else if (itemName.equals("车辆的发动机、变速箱和冷却系统等有无明显的液体泄漏")){
                mapKey = "fdjywmxytxl";
                return mapKey;
            }else if (itemName.equals("是否带OBD系统")){
                mapKey = "sfyobd";
                return mapKey;
            }else if (itemName.equals("轮胎气压是否正常")){
                mapKey = "ltqysfzc";
                return mapKey;
            }else if (itemName.equals("轮胎是否干燥、清洁")){
                mapKey = "ltsfgzqj";
                return mapKey;
            }else if (itemName.equals("是否关闭车上空调、暖风等附属设备")){
                mapKey = "sfgbktnfsb";
                return mapKey;
            }else if (itemName.equals("是否已经中断车辆上可能影响测试正常进行的功能，如ARS、ESP、EPC牵引力控制或自动制动系统等")){
                mapKey = "zdknyxcsdgn";
                return mapKey;
            }else if (itemName.equals("车辆油箱和油品是否异常")){
                mapKey = "clyxhyp";
                return mapKey;
            }else if (itemName.equals("是否适合工况法检测")){
                mapKey = "gkfjy";
                return mapKey;
            }else if (itemName.equals("(汽油车)曲轴箱通风系统是否正常")){
                mapKey = "qzxtfxt";
                return mapKey;
            }else if (itemName.equals("(柴油车)发动机燃油系统是否采用电控泵")){
                mapKey = "fdjryxt";
                return mapKey;
            }else if (itemName.equals("机动车环保信息随车清单是否一致")){
                mapKey = "scqdsfyz";
                return mapKey;
            }
        return mapKey;
    }

    /**【String[] 转 ArrayList<String>类型】**/
    public static ArrayList<String> strToArrayList(String [] data){
        ArrayList<String> list = null;
        if (data != null){
            list = new ArrayList<>();
            for (int i = 0 ; i < data.length ; i ++){
                list.add(data[i]);
            }
        }
        return list;
    }

}
