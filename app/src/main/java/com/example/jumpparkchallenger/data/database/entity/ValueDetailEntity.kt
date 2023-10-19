package com.example.jumpparkchallenger.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "value_detail",
    foreignKeys = [ForeignKey(
        entity = PriceEntity::class,
        parentColumns = ["id"],
        childColumns = ["table_price_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class ValueDetailEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    val price: String?,
    val period: Int?,
    @ColumnInfo(name = "price_type")
    val priceType: String?,

    @ColumnInfo(name = "table_price_id")
    val tablePriceId: Int
)