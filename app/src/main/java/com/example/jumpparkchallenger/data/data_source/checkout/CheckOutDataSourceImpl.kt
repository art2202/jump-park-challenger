package com.example.jumpparkchallenger.data.data_source.checkout

import com.example.jumpparkchallenger.data.database.dao.PaymentMethodDao
import com.example.jumpparkchallenger.data.database.entity.PaymentMethodEntity

class CheckOutDataSourceImpl(private val paymentMethodDao: PaymentMethodDao) : CheckOutDataSource {
    override suspend fun getPaymentsMethod(): List<PaymentMethodEntity> {
        return paymentMethodDao.getAll()
    }
}