package com.example.jumpparkchallenger.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "payment_method")
data class PaymentMethodEntity(
    @PrimaryKey
    val id: Int,

    val name: String,

    val total : Double = 0.0
)