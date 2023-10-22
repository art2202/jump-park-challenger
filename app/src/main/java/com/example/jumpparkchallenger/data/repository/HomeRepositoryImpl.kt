package com.example.jumpparkchallenger.data.repository

import com.example.jumpparkchallenger.data.data_source.home.HomeDataSource
import com.example.jumpparkchallenger.data.data_source.home.HomeLocalDataSource
import com.example.jumpparkchallenger.data.database.dao.VehicleDao
import com.example.jumpparkchallenger.data.database.entity.ValueDetailEntity
import com.example.jumpparkchallenger.data.mapper.PaymentMethodEntityToPaymentMethodMapper
import com.example.jumpparkchallenger.data.mapper.PaymentMethodResponseToPaymentMethodEntityMapper
import com.example.jumpparkchallenger.data.mapper.PricesResponseToPriceEntityMapper
import com.example.jumpparkchallenger.data.mapper.ValueResponseToValueEntityMapper
import com.example.jumpparkchallenger.data.models.home.HomeData
import com.example.jumpparkchallenger.domain.entities.home.HomeInfos
import com.example.jumpparkchallenger.domain.entities.home.PaymentMethod
import com.example.jumpparkchallenger.domain.repository.HomeRepository

class HomeRepositoryImpl(
    private val homeDataSource: HomeDataSource,
    private val homeLocalDataSource: HomeLocalDataSource,
    private val pricesResponseToPriceEntityMapper: PricesResponseToPriceEntityMapper,
    private val paymentMethodResponseToPaymentMethodEntityMapper: PaymentMethodResponseToPaymentMethodEntityMapper,
    private val paymentMethodEntityToPaymentMethodMapper: PaymentMethodEntityToPaymentMethodMapper,
    private val valueResponseToValueEntityMapper: ValueResponseToValueEntityMapper,
    private val vehicleDao: VehicleDao
) : HomeRepository {
    override suspend fun getData(): HomeInfos {

        val userEntity = homeLocalDataSource.getUser()
        val establishmentEntity = homeLocalDataSource.getEstablishment()
        try {
            val data = homeDataSource.getData(userEntity?.id ?: 0, establishmentEntity?.id ?: 0)
            save(data)
        }
        catch (e : Exception){
            return HomeInfos(
                establishmentEntity!!.vacanciesMarks,
                vehicleDao.getAllVehicles().size,
                getPaymentMethods()
            )
        }

        return HomeInfos(
            establishmentEntity!!.vacanciesMarks,
            vehicleDao.getAllVehicles().size,
            getPaymentMethods()
        )
    }

    private suspend fun getPaymentMethods(): List<PaymentMethod> {
        val paymentMethodsEntities = homeLocalDataSource.getPaymentMethodEntity()

        return paymentMethodsEntities.map { paymentMethodEntityToPaymentMethodMapper.map(it) }
    }

    private suspend fun save(data: HomeData) {

        val valuesEntitys : MutableList<ValueDetailEntity> = mutableListOf()

        data.pricesResponseData.forEach { priceDataResponse ->
            priceDataResponse?.values?.forEach { valueResponseData ->
                valuesEntitys.add(valueResponseToValueEntityMapper.map(valueResponseData))
            }
        }
        val pricesEntitys = data.pricesResponseData.map {
            pricesResponseToPriceEntityMapper.map(it!!)
        }
        val paymentsMethodsEntity = data.paymentMethodsResponseData.map {
            paymentMethodResponseToPaymentMethodEntityMapper.map(it!!)
        }

        pricesEntitys.forEach { homeLocalDataSource.insertPrice(it) }
        paymentsMethodsEntity.forEach { homeLocalDataSource.insertPaymentMethod(it) }
        valuesEntitys.forEach { homeLocalDataSource.insertValueDetail(it) }
    }
}