package com.example.jumpparkchallenger.data.data_source.home

import com.example.jumpparkchallenger.data.database.entity.EstablishmentEntity
import com.example.jumpparkchallenger.data.database.entity.PaymentMethodEntity
import com.example.jumpparkchallenger.data.database.entity.PriceEntity
import com.example.jumpparkchallenger.data.database.entity.UserEntity
import com.example.jumpparkchallenger.data.database.entity.ValueDetailEntity
import com.example.jumpparkchallenger.domain.entities.home.PaymentMethod
import com.example.jumpparkchallenger.domain.entities.home.ValueDetail

interface HomeLocalDataSource {

    suspend fun insertPrice(priceEntity: PriceEntity)

    suspend fun insertPaymentMethod(paymentMethodEntity: PaymentMethodEntity)

    suspend fun insertValueDetail(valueDetailEntity: ValueDetailEntity)

    suspend fun getPrices() : List<PriceEntity>

    suspend fun getPaymentMethodEntity() : List<PaymentMethodEntity>

    suspend fun getValueDetail(id: Int): List<ValueDetailEntity>

    suspend fun getUser() : UserEntity?

    suspend fun getEstablishment() : EstablishmentEntity?
}