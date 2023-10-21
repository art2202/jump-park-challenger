package com.example.jumpparkchallenger.data.data_source.checkout

import com.example.jumpparkchallenger.data.database.entity.PaymentMethodEntity
import com.example.jumpparkchallenger.data.database.entity.VehicleEntity

interface CheckOutDataSource {

    suspend fun getPaymentsMethod() : List<PaymentMethodEntity>

    suspend fun deleteVehicle(vehicleEntity: VehicleEntity)

    suspend fun savePayment(paymentMethodEntity: PaymentMethodEntity)
}