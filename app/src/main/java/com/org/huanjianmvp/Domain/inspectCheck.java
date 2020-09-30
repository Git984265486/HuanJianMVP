package com.org.huanjianmvp.Domain;

/**
 * 作为外检检验的子项数据
 * Created by Administrator on 2020/9/10.
 */

public class inspectCheck {

    private String title;           //检验标题
    private String result;          //检验结果
    private boolean isCheck;        //是否合格

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
