package com.example.jumpparkchallenger.data.data_source.checkout

import com.example.jumpparkchallenger.data.database.dao.PaymentMethodDao
import com.example.jumpparkchallenger.data.database.dao.VehicleDao
import com.example.jumpparkchallenger.data.database.entity.PaymentMethodEntity
import com.example.jumpparkchallenger.data.database.entity.VehicleEntity

class CheckOutDataSourceImpl(
    private val paymentMethodDao: PaymentMethodDao,
    private val vehicleDao: VehicleDao
) : CheckOutDataSource {
    override suspend fun getPaymentsMethod(): List<PaymentMethodEntity> {
        return paymentMethodDao.getAll()
    }

    override suspend fun deleteVehicle(vehicleEntity: VehicleEntity) {
        vehicleDao.deleteVehicle(vehicleEntity)
    }

    override suspend fun savePayment(paymentMethodEntity: PaymentMethodEntity) {
        paymentMethodDao.updateTotalById(paymentMethodEntity.id, paymentMethodEntity.total)
    }
}