package cn.zhaoliang5156.mddjoke.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by zhaoliang on 2017/10/9.
 * http://route.showapi.com/341-3?showapi_appid=47411&page=&30=&showapi_sign=fff46234902f479aa89ea911b7c59b15&maxResult=50
 */
interface JokeApi {

    @GET("341-1/?showapi_appid=47411&&showapi_sign=fff46234902f479aa89ea911b7c59b15")
    fun getText(@Query("page") page: Int, @Query("maxResult") maxResult: Int): Observable<TextRespnose>

    @GET("341-2/?showapi_appid=47411&&showapi_sign=fff46234902f479aa89ea911b7c59b15")
    fun getImage(@Query("page") page: Int, @Query("maxResult") maxResult: Int): Observable<ImageRespnose>

    @GET("341-3/?showapi_appid=47411&&showapi_sign=fff46234902f479aa89ea911b7c59b15")
    fun getGif(@Query("page") page: Int, @Query("maxResult") maxResult: Int): Observable<GifResponse>


}