package com.org.huanjianmvp.Contrast

import android.content.SharedPreferences

/**
 * Created by Administrator on 2020/8/28.
 */
interface SettingContrast {

    interface Model{

        /**【保存数据】**/
        fun saveAction(map : Map<String,String> , preferences: SharedPreferences)

        /**【获取初始化数据】**/
        fun initDataAction(preferences: SharedPreferences)

    }

    interface ViewAndPresenter{

        /**【请求保存数据】**/
        fun requestSave(map : Map<String,String> , preferences: SharedPreferences)

        /**【请求初始数据】**/
        fun requestInitData(preferences: SharedPreferences)

        /**【响应初始数据】**/
        fun responseInitData(map : Map<String,String>)

    }

}