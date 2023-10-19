package com.example.jumpparkchallenger.data.api

import com.example.jumpparkchallenger.core.App
import com.example.jumpparkchallenger.core.utils.SharedPreferenceHelper
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestApi {

    private val sharedPreferenceHelper = SharedPreferenceHelper(App.instance)

    private val token = sharedPreferenceHelper.getToken()
    private val okHttpClient = OkHttpClient.Builder()
        .build()

    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl("https://dev.app.jumpparkapi.com.br/api/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    fun getRetrofit() = retrofit

}