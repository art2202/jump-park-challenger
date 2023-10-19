package com.example.jumpparkchallenger.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "price")
data class PriceEntity(
    @PrimaryKey
    val id: Int?,

    @ColumnInfo(name = "price_type")
    val priceType: String?,

    val tolerance: Int?,
    @ColumnInfo(name = "maximum_period")
    val maximumPeriod: Int?,
    @ColumnInfo(name = "maximum_value")
    val maximumValue: String?
)