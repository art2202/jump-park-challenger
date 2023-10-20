package com.example.jumpparkchallenger.data.repository

import com.example.jumpparkchallenger.domain.entities.Vehicle
import com.example.jumpparkchallenger.domain.repository.CheckOutRepository
import java.util.Date
import java.util.concurrent.TimeUnit

class CheckOutRepositoryImpl : CheckOutRepository{
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

            if (remainingTime <= item.period) {
                totalCost += item.price.toDouble()
                break
            } else {
                remainingTime -= item.period
                totalCost += item.price.toDouble()
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