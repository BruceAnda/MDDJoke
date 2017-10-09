package cn.zhaoliang5156.mddjoke.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * joke 服务，用来请求api
 * Created by zhaoliang on 2017/10/9.
 * http://route.showapi.com/341-3?showapi_appid=47411&page=&30=&showapi_sign=fff46234902f479aa89ea911b7c59b15&maxResult=50
 */
class JokeService {

    val url = "http://route.showapi.com/"
    //val url = "http://fy.iciba.com/"

    fun InstanceJokeApi(): JokeApi {
        val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit.create(JokeApi::class.java)
    }
}