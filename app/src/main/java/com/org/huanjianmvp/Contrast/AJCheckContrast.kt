package com.org.huanjianmvp.Contrast

/**
 * Created by Administrator on 2020/9/4.
 */
interface AJCheckContrast {

    interface Model{
        /**【请求数据处理】**/
        fun actionData()
    }

    interface ViewAndPresenter{

        /**【请求数据】**/
        fun requestData()

        /**【相应请求数据】**/
        fun responseData(list: List<String>)
    }

}