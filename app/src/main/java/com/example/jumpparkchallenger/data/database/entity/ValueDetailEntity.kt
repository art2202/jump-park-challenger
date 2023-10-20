package com.example.jumpparkchallenger.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "value_detail",
    foreignKeys = [ForeignKey(
        entity = PriceEntity::class,
        parentColumns = ["price_type"],
        childColumns = ["price_type"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class ValueDetailEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    val price: String,

    val period: Int,

    val since : Int,

    @ColumnInfo(name = "price_type")
    val priceType: String,

)