package com.example.jumpparkchallenger.core.utils

import androidx.room.Room
import com.example.jumpparkchallenger.core.App
import com.example.jumpparkchallenger.data.api.ApiService
import com.example.jumpparkchallenger.data.api.RestApi
import com.example.jumpparkchallenger.data.data_source.LoginDataSource
import com.example.jumpparkchallenger.data.data_source.LoginDataSourceImpl
import com.example.jumpparkchallenger.data.data_source.LoginLocalDataSource
import com.example.jumpparkchallenger.data.data_source.LoginLocalDataSourceImpl
import com.example.jumpparkchallenger.data.database.AppDatabase
import com.example.jumpparkchallenger.data.mapper.EstablishmentDataResponseToEstablishmentEntityMapper
import com.example.jumpparkchallenger.data.mapper.LoginDataResponseToHomeInfosMapper
import com.example.jumpparkchallenger.data.mapper.UserDataResponseToUserEntityMapper
import com.example.jumpparkchallenger.data.repository.LoginRepositoryImpl
import com.example.jumpparkchallenger.domain.repository.LoginRepository
import com.example.jumpparkchallenger.domain.usecases.CheckToken
import com.example.jumpparkchallenger.domain.usecases.Login
import com.example.jumpparkchallenger.presentation.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modules = module {
    single<ApiService> { RestApi.getRetrofit().create(ApiService::class.java) }
    single { SharedPreferenceHelper(get()) }

    single{LoginDataResponseToHomeInfosMapper()}
    single { UserDataResponseToUserEntityMapper() }
    single { EstablishmentDataResponseToEstablishmentEntityMapper() }

    single<LoginLocalDataSource> { LoginLocalDataSourceImpl(get(), get(), get()) }
    single<LoginDataSource> { LoginDataSourceImpl(get()) }

    single<LoginRepository> { LoginRepositoryImpl(get(), get(), get(), get(), get()) }

    single{ Login(get()) }
    single { CheckToken(get()) }
}

val viewModelModule = module {
    viewModel { LoginViewModel(get(), get()) }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(
            App.instance,
            AppDatabase::class.java,
            "jump-park-challenger"
        ).build()
    }

    single { get<AppDatabase>().userDao() }
    single { get<AppDatabase>().establishmentDao() }
}