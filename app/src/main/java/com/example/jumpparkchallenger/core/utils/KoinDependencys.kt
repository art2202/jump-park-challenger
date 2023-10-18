package com.example.jumpparkchallenger.core.utils

import com.example.jumpparkchallenger.data.api.ApiService
import com.example.jumpparkchallenger.data.api.RestApi
import com.example.jumpparkchallenger.data.data_source.LoginDataSource
import com.example.jumpparkchallenger.data.data_source.LoginDataSourceImpl
import com.example.jumpparkchallenger.data.mapper.LoginDataResponseToHomeInfosMapper
import com.example.jumpparkchallenger.data.repository.LoginRepositoryImpl
import com.example.jumpparkchallenger.domain.repository.LoginRepository
import com.example.jumpparkchallenger.domain.usecases.Login
import com.example.jumpparkchallenger.presentation.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modules = module {
    single<ApiService> { RestApi.getRetrofit().create(ApiService::class.java) }

    single{LoginDataResponseToHomeInfosMapper()}

    single<LoginDataSource> { LoginDataSourceImpl(get()) }
    single<LoginRepository> { LoginRepositoryImpl(get(), get()) }

    single{ Login(get()) }
}

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
}