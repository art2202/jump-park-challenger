package com.example.jumpparkchallenger.core.utils

import com.example.jumpparkchallenger.data.api.ApiService
import com.example.jumpparkchallenger.data.api.RestApi
import com.example.jumpparkchallenger.data.data_source.LoginDataSource
import com.example.jumpparkchallenger.data.data_source.LoginDataSourceImpl
import org.koin.dsl.module

val modules = module {
    single<ApiService> { RestApi.getRetrofit().create(ApiService::class.java) }
    single<LoginDataSource> { LoginDataSourceImpl(get()) }
}