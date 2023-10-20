package com.example.jumpparkchallenger.core.utils

import androidx.room.Room
import com.example.jumpparkchallenger.core.App
import com.example.jumpparkchallenger.data.api.ApiService
import com.example.jumpparkchallenger.data.api.RestApi
import com.example.jumpparkchallenger.data.data_source.checkin.CheckInDataSource
import com.example.jumpparkchallenger.data.data_source.checkin.CheckInDataSourceImpl
import com.example.jumpparkchallenger.data.data_source.home.HomeDataSource
import com.example.jumpparkchallenger.data.data_source.home.HomeDataSourceImpl
import com.example.jumpparkchallenger.data.data_source.home.HomeLocalDataSource
import com.example.jumpparkchallenger.data.data_source.home.HomeLocalDataSourceImpl
import com.example.jumpparkchallenger.data.data_source.login.LoginDataSource
import com.example.jumpparkchallenger.data.data_source.login.LoginDataSourceImpl
import com.example.jumpparkchallenger.data.data_source.login.LoginLocalDataSource
import com.example.jumpparkchallenger.data.data_source.login.LoginLocalDataSourceImpl
import com.example.jumpparkchallenger.data.data_source.vehicle_list.VehicleListDataSource
import com.example.jumpparkchallenger.data.data_source.vehicle_list.VehicleListDataSourceImpl
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
import com.example.jumpparkchallenger.data.mapper.VehicleEntityToVehicleMapper
import com.example.jumpparkchallenger.data.mapper.VehicleToVehicleEntityMapper
import com.example.jumpparkchallenger.data.repository.CheckInRepositoryImpl
import com.example.jumpparkchallenger.data.repository.CheckOutRepositoryImpl
import com.example.jumpparkchallenger.data.repository.HomeRepositoryImpl
import com.example.jumpparkchallenger.data.repository.LoginRepositoryImpl
import com.example.jumpparkchallenger.data.repository.VehicleListRepositoryImpl
import com.example.jumpparkchallenger.domain.entities.CalculateValue
import com.example.jumpparkchallenger.domain.repository.CheckInRepository
import com.example.jumpparkchallenger.domain.repository.CheckOutRepository
import com.example.jumpparkchallenger.domain.repository.HomeRepository
import com.example.jumpparkchallenger.domain.repository.LoginRepository
import com.example.jumpparkchallenger.domain.repository.VehicleListRepository
import com.example.jumpparkchallenger.domain.usecases.CheckToken
import com.example.jumpparkchallenger.domain.usecases.GetPrices
import com.example.jumpparkchallenger.domain.usecases.LoadHomeInfo
import com.example.jumpparkchallenger.domain.usecases.Login
import com.example.jumpparkchallenger.domain.usecases.SaveVehicle
import com.example.jumpparkchallenger.domain.usecases.VehicleList
import com.example.jumpparkchallenger.presentation.checkin.CheckInViewModel
import com.example.jumpparkchallenger.presentation.checkout.CheckOutViewModel
import com.example.jumpparkchallenger.presentation.home.HomeViewModel
import com.example.jumpparkchallenger.presentation.login.LoginViewModel
import com.example.jumpparkchallenger.presentation.vehicle_list.VehicleListViewModel
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
    single { get<AppDatabase>().vehicleDao() }
}
val dataSourceModule = module{
    single<LoginLocalDataSource> { LoginLocalDataSourceImpl(get(), get(), get()) }
    single<LoginDataSource> { LoginDataSourceImpl(get()) }
    single<HomeDataSource> { HomeDataSourceImpl(get(), get()) }
    single<HomeLocalDataSource> { HomeLocalDataSourceImpl(get(), get(), get(), get(), get()) }
    single<CheckInDataSource> { CheckInDataSourceImpl(get(), get(), get()) }
    single<VehicleListDataSource> { VehicleListDataSourceImpl(get(), get(), get()) }
}
val mapperModule = module{
    single { LoginDataResponseToHomeInfosMapper() }
    single { UserDataResponseToUserEntityMapper() }
    single { EstablishmentDataResponseToEstablishmentEntityMapper() }

    single { PaymentMethodResponseToPaymentMethodEntityMapper() }
    single { PaymentMethodEntityToPaymentMethodMapper() }

    single { PricesResponseToPriceEntityMapper() }
    single { PriceEntityToPriceMapper(get()) }

    single { ValueResponseToValueEntityMapper() }
    single { ValueDetailEntityToValueDetailMapper() }

    single { VehicleToVehicleEntityMapper() }
    single { VehicleEntityToVehicleMapper(get()) }

}
val repositoryModule = module{
    single<LoginRepository> { LoginRepositoryImpl(get(), get(), get(), get(), get()) }
    single<HomeRepository> { HomeRepositoryImpl(get(), get(), get(), get(), get(), get()) }
    single<CheckInRepository> { CheckInRepositoryImpl(get(), get(), get()) }
    single<VehicleListRepository> { VehicleListRepositoryImpl(get(), get()) }
    single<CheckOutRepository> { CheckOutRepositoryImpl() }
}
val useCaseModule = module {
    single{ Login(get()) }
    single { CheckToken(get()) }
    single { LoadHomeInfo(get()) }
    single { GetPrices(get()) }
    single { SaveVehicle(get()) }
    single { VehicleList(get()) }
    single { CalculateValue(get()) }
}
val viewModelModule = module {
    viewModel { LoginViewModel(get(), get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { CheckInViewModel(get(), get()) }
    viewModel { VehicleListViewModel(get()) }
    viewModel { CheckOutViewModel(get()) }
}