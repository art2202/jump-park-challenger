package com.example.jumpparkchallenger.data.data_source.home

import com.example.jumpparkchallenger.data.database.dao.EstablishmentDao
import com.example.jumpparkchallenger.data.database.dao.PaymentMethodDao
import com.example.jumpparkchallenger.data.database.dao.PriceDao
import com.example.jumpparkchallenger.data.database.dao.UserDao
import com.example.jumpparkchallenger.data.database.dao.ValueDetailDao
import com.example.jumpparkchallenger.data.database.entity.EstablishmentEntity
import com.example.jumpparkchallenger.data.database.entity.PaymentMethodEntity
import com.example.jumpparkchallenger.data.database.entity.PriceEntity
import com.example.jumpparkchallenger.data.database.entity.UserEntity
import com.example.jumpparkchallenger.data.database.entity.ValueDetailEntity
import com.example.jumpparkchallenger.domain.entities.home.PaymentMethod
import com.example.jumpparkchallenger.domain.entities.home.ValueDetail

class HomeLocalDataSourceImpl(private val userDao: UserDao,
                              private val establishmentDao: EstablishmentDao,
                              private val paymentMethodDao: PaymentMethodDao,
                              private val priceDao: PriceDao,
                              private val valueDetailDao: ValueDetailDao
) : HomeLocalDataSource {
    override suspend fun insertPrice(priceEntity: PriceEntity) = priceDao.insertPrice(priceEntity)

    override suspend fun insertPaymentMethod(paymentMethodEntity: PaymentMethodEntity) {
        paymentMethodDao.insertPaymentMethod(paymentMethodEntity)
    }

    override suspend fun insertValueDetail(valueDetailEntity: ValueDetailEntity) {
        valueDetailDao.insertValueDetail(valueDetailEntity)
    }

    override suspend fun getPrices(): List<PriceEntity> = priceDao.getAllPrices()

    override suspend fun getPaymentMethodEntity(): List<PaymentMethodEntity> {
        return paymentMethodDao.getAll()
    }

    override suspend fun getValueDetail(id: Int): List<ValueDetailEntity> {
        return valueDetailDao.getValueDetailsByPriceId(id)
    }

    override suspend fun getUser(): UserEntity? = userDao.getUser()

    override suspend fun getEstablishment(): EstablishmentEntity? = establishmentDao.getEstablishment()


}