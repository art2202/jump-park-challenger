package com.example.jumpparkchallenger.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = "vehicle",
    foreignKeys = [
        ForeignKey(
            entity = PriceEntity::class,
            parentColumns = ["price_type"],
            childColumns = ["price_type"],
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
data class VehicleEntity(
    @PrimaryKey
    val plate: String,
    val model: String,
    val color: String,
    @ColumnInfo("price_type")
    val priceType : String
)