package com.example.jumpparkchallenger.core.utils

import androidx.room.Room
import com.example.jumpparkchallenger.core.App
import com.example.jumpparkchallenger.data.api.ApiService
import com.example.jumpparkchallenger.data.api.RestApi
import com.example.jumpparkchallenger.data.data_source.home.HomeDataSource
import com.example.jumpparkchallenger.data.data_source.home.HomeDataSourceImpl
import com.example.jumpparkchallenger.data.data_source.home.HomeLocalDataSource
import com.example.jumpparkchallenger.data.data_source.home.HomeLocalDataSourceImpl
import com.example.jumpparkchallenger.data.data_source.login.LoginDataSource
import com.example.jumpparkchallenger.data.data_source.login.LoginDataSourceImpl
import com.example.jumpparkchallenger.data.data_source.login.LoginLocalDataSource
import com.example.jumpparkchallenger.data.data_source.login.LoginLocalDataSourceImpl
import com.example.jumpparkchallenger.data.database.AppDatabase
import com.example.jumpparkchallenger.data.mapper.EstablishmentDataResponseToEstablishmentEntityMapper
import com.example.jumpparkchallenger.data.mapper.LoginDataResponseToHomeInfosMapper
import com.example.jumpparkchallenger.data.mapper.PaymentMethodEntityToPaymentMethodMapper
import com.example.jumpparkchallenger.data.mapper.PaymentMethodResponseToPaymentMethodEntityMapper
import com.example.jumpparkchallenger.data.mapper.PriceEntityToPriceMapper
import com.example.jumpparkchallenger.data.mapper.PricesResponseToPriceEntityMapper
import com.example.jumpparkchallenger.data.mapper.UserDataResponseToUserEntityMapper
import com.example.jumpparkchallenger.data.mapper.ValueDetailEntityToValueDetailMapper
import com.example.jumpparkchallenger.data.mapper.ValueResponseToValueEntityMapper
import com.example.jumpparkchallenger.data.repository.HomeRepositoryImpl
import com.example.jumpparkchallenger.data.repository.LoginRepositoryImpl
import com.example.jumpparkchallenger.domain.repository.HomeRepository
import com.example.jumpparkchallenger.domain.repository.LoginRepository
import com.example.jumpparkchallenger.domain.usecases.CheckToken
import com.example.jumpparkchallenger.domain.usecases.LoadHomeInfo
import com.example.jumpparkchallenger.domain.usecases.Login
import com.example.jumpparkchallenger.presentation.home.HomeViewModel
import com.example.jumpparkchallenger.presentation.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val utilsModule = module {
    single<ApiService> { RestApi.getRetrofit().create(ApiService::class.java) }
    single { SharedPreferenceHelper(get()) }
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
    single { get<AppDatabase>().PriceDao() }
    single { get<AppDatabase>().PaymentMethodDao() }
    single { get<AppDatabase>().ValueDetailDao() }
}
val dataSourceModule = module{
    single<LoginLocalDataSource> { LoginLocalDataSourceImpl(get(), get(), get()) }
    single<LoginDataSource> { LoginDataSourceImpl(get()) }
    single<HomeDataSource> { HomeDataSourceImpl(get(), get()) }
    single<HomeLocalDataSource> { HomeLocalDataSourceImpl(get(), get()) }
}
val mapperModule = module{
    single { LoginDataResponseToHomeInfosMapper() }
    single { UserDataResponseToUserEntityMapper() }
    single { EstablishmentDataResponseToEstablishmentEntityMapper() }

    single { PaymentMethodResponseToPaymentMethodEntityMapper() }
    single { PaymentMethodEntityToPaymentMethodMapper() }

    single { PricesResponseToPriceEntityMapper() }
    single { PriceEntityToPriceMapper(get(), get()) }

    single { ValueResponseToValueEntityMapper(get()) }
    single { ValueDetailEntityToValueDetailMapper() }

}
val repositoryModule = module{
    single<LoginRepository> { LoginRepositoryImpl(get(), get(), get(), get(), get()) }
    single<HomeRepository> { HomeRepositoryImpl(get(), get(), get(), get(), get(), get(), get(), get()) }
}
val useCaseModule = module {
    single{ Login(get()) }
    single { CheckToken(get()) }
    single { LoadHomeInfo(get()) }
}

val viewModelModule = module {
    viewModel { LoginViewModel(get(), get()) }
    viewModel { HomeViewModel(get()) }
}