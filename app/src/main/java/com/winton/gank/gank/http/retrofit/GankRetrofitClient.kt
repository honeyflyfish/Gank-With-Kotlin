package com.winton.gank.gank.http.retrofit

import com.winton.gank.gank.http.BaseGankSubscriber
import com.winton.gank.gank.http.ScHelper
import com.winton.gank.gank.http.api.GankApi
import com.winton.gank.gank.http.response.gank.TodayResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author: winton
 * @time: 2018/10/9 下午8:25
 * @desc: gank 服务端的RetrofitClient
 */

class GankRetrofitClient(val okHttpClient: OkHttpClient) {

    private lateinit var server:GankApi

    init {
        val client = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(GankApi.baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        server = client.create(GankApi::class.java)
    }

    /***********实现的方法************/

    /**
     * 今天
     */
    fun today(subscriber: BaseGankSubscriber<TodayResponse>){
        server.today().compose(ScHelper.compose())
                .subscribe(subscriber)
    }


}