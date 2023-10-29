package com.example.jumpparkchallenger.data.repository

import com.example.jumpparkchallenger.data.data_source.checkout.CheckOutDataSource
import com.example.jumpparkchallenger.data.mapper.PaymentMethodEntityToPaymentMethodMapper
import com.example.jumpparkchallenger.data.mapper.PaymentMethodToPaymentMethodEntityMapper
import com.example.jumpparkchallenger.data.mapper.VehicleToVehicleEntityMapper
import com.example.jumpparkchallenger.domain.entities.Vehicle
import com.example.jumpparkchallenger.domain.entities.home.PaymentMethod
import com.example.jumpparkchallenger.domain.repository.CheckOutRepository
import java.util.Date
import java.util.concurrent.TimeUnit

class CheckOutRepositoryImpl(
    private val checkOutDataSource: CheckOutDataSource,
    private val paymentMethodEntityToPaymentMethodMapper: PaymentMethodEntityToPaymentMethodMapper,
    private val vehicleToVehicleEntityMapper: VehicleToVehicleEntityMapper,
    private val paymentMethodToPaymentMethodEntityMapper : PaymentMethodToPaymentMethodEntityMapper
    ) : CheckOutRepository{
    override suspend fun checkOut(vehicle: Vehicle, paymentSelected: PaymentMethod) {
        val vehicleEntity = vehicleToVehicleEntityMapper.map(vehicle)
        checkOutDataSource.deleteVehicle(vehicleEntity)
        val paymentMethodEntity = paymentMethodToPaymentMethodEntityMapper.map(paymentSelected)
        checkOutDataSource.savePayment(paymentMethodEntity)

    }

    override suspend fun getPaymentsMethod(): List<PaymentMethod> {
        val paymentsEntity = checkOutDataSource.getPaymentsMethod()
        return paymentsEntity.map {
            paymentMethodEntityToPaymentMethodMapper.map(it)
        }
    }

    override fun calculateValue(vehicle: Vehicle): Pair<Int, Double> {
        val price = vehicle.price
        val time = getMinutes(vehicle.date)

        if(price.tolerance >= time){
            return Pair(time, 0.0)
        }

        var remainingTime = time
        var totalCost = 0.0

        val sortedItems = price.valueDetails.sortedBy { it.since }

        for (item in sortedItems) {
            if (remainingTime <= 0) break

            while (remainingTime > item.period) {
                totalCost += item.price.toDouble()
                remainingTime -= item.period
            }

            if (remainingTime > 0 && remainingTime <= item.period) {
                totalCost += item.price.toDouble()
                break
            }
        }

        return Pair(time, totalCost)
    }

    private fun getMinutes(checkInDate: Date) : Int {
        val checkOutDate = Date()
        val diff = checkOutDate.time - checkInDate.time
        return TimeUnit.MILLISECONDS.toMinutes(diff).toInt()
    }
}