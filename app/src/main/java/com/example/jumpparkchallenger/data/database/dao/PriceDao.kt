package com.example.jumpparkchallenger.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jumpparkchallenger.data.database.entity.PriceEntity

@Dao
interface PriceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertPrice(price: PriceEntity): Long

    @Query("SELECT * FROM price WHERE id = :establishmentId")
     fun getPriceByEstablishmentId(establishmentId: Int): PriceEntity?

    @Query("SELECT * FROM price")
     fun getAllPrices(): List<PriceEntity>

    @Delete
     fun deletePrice(price: PriceEntity)

    @Query("DELETE FROM price")
     fun deleteAllPrices()
}