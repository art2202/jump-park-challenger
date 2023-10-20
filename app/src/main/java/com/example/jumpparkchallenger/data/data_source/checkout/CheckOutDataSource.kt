package com.example.jumpparkchallenger.data.data_source.checkout

import com.example.jumpparkchallenger.data.database.entity.PaymentMethodEntity

interface CheckOutDataSource {

    suspend fun getPaymentsMethod() : List<PaymentMethodEntity>
}