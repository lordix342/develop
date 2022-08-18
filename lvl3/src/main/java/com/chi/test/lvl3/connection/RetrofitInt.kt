package com.chi.test.lvl3

import com.chi.test.lvl3.models.ModelZooItem
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitInt {
    @GET("v1/forecast.json?key=bdbb753421a44e00a37171142222106&days=5&aqi=no&alerts=no")
    suspend fun getZoo(): Response<Array<ModelZooItem>>
}

object ApiInstance {
    private val httpLoggingInterceptor = HttpLoggingInterceptor()

    init {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .build()
    private val retrofit by lazy {
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://api.weatherapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }
    val api: RetrofitInt by lazy {
        retrofit.create(RetrofitInt::class.java)
    }
}
class ApiRepo {
    suspend fun getZooItem(): Response<Array<ModelZooItem>> {
        return ApiInstance.api.getZoo()
    }
}