package com.example.jumpparkchallenger.data.repository

import com.example.jumpparkchallenger.data.data_source.home.HomeDataSource
import com.example.jumpparkchallenger.data.data_source.home.HomeLocalDataSource
import com.example.jumpparkchallenger.data.database.entity.ValueDetailEntity
import com.example.jumpparkchallenger.data.mapper.PaymentMethodEntityToPaymentMethodMapper
import com.example.jumpparkchallenger.data.mapper.PaymentMethodResponseToPaymentMethodEntityMapper
import com.example.jumpparkchallenger.data.mapper.PriceEntityToPriceMapper
import com.example.jumpparkchallenger.data.mapper.PricesResponseToPriceEntityMapper
import com.example.jumpparkchallenger.data.mapper.ValueResponseToValueEntityMapper
import com.example.jumpparkchallenger.data.models.home.HomeData
import com.example.jumpparkchallenger.domain.entities.home.PaymentMethod
import com.example.jumpparkchallenger.domain.entities.home.Price
import com.example.jumpparkchallenger.domain.repository.HomeRepository

class HomeRepositoryImpl(
    private val homeDataSource: HomeDataSource,
    private val homeLocalDataSource: HomeLocalDataSource,
    private val pricesResponseToPriceEntityMapper: PricesResponseToPriceEntityMapper,
    private val priceEntityToPriceMapper: PriceEntityToPriceMapper,
    private val paymentMethodResponseToPaymentMethodEntityMapper: PaymentMethodResponseToPaymentMethodEntityMapper,
    private val paymentMethodEntityToPaymentMethodMapper: PaymentMethodEntityToPaymentMethodMapper,
    private val valueResponseToValueEntityMapper: ValueResponseToValueEntityMapper
) : HomeRepository {
    override suspend fun getData(): Pair<List<Price>, List<PaymentMethod>> {

        val userEntity = homeLocalDataSource.getUser()
        val establishmentEntity = homeLocalDataSource.getEstablishment()
        val data = homeDataSource.getData(userEntity?.id ?: 0, establishmentEntity?.id ?: 0)
        save(data)
        return getDatabaseInfo()
    }

    private suspend fun getDatabaseInfo(): Pair<List<Price>, List<PaymentMethod>> {
        val pricesEntity = homeLocalDataSource.getPrices()
        val paymentMethodsEntities = homeLocalDataSource.getPaymentMethodEntity()
        val valuesDetails = pricesEntity.map { homeLocalDataSource.getValueDetail(it.id!!) }.flatten()

        val prices = pricesEntity.map { priceEntityToPriceMapper.map(Pair(it, valuesDetails)) }
        val paymentMethods = paymentMethodsEntities.map { paymentMethodEntityToPaymentMethodMapper.map(it) }

        return Pair(prices, paymentMethods)
    }

    private suspend fun save(data: HomeData) {

        var valuesEntitys : List<ValueDetailEntity>? = null
        data.pricesResponseData.forEach {
            valuesEntitys = it!!.values.map {values -> valueResponseToValueEntityMapper.map(values) }
        }
        val pricesEntitys = data.pricesResponseData.map {
            pricesResponseToPriceEntityMapper.map(it!!)
        }
        val paymentsMethodsEntity = data.paymentMethodsResponseData.map {
            paymentMethodResponseToPaymentMethodEntityMapper.map(it!!)
        }

        pricesEntitys.forEach { homeLocalDataSource.insertPrice(it) }
        paymentsMethodsEntity.forEach { homeLocalDataSource.insertPaymentMethod(it) }
        valuesEntitys?.forEach { homeLocalDataSource.insertValueDetail(it) }
    }
}