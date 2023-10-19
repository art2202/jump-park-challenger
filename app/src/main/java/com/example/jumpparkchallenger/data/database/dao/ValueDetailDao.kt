package com.example.jumpparkchallenger.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jumpparkchallenger.data.database.entity.ValueDetailEntity

@Dao
interface ValueDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertValueDetail(valueDetail: ValueDetailEntity): Long

    @Query("SELECT * FROM value_detail WHERE id = :tablePriceId")
     fun getValueDetailsByPriceId(tablePriceId: Int): List<ValueDetailEntity>

    @Delete
     fun deleteValueDetail(valueDetail: ValueDetailEntity)

    @Query("DELETE FROM value_detail WHERE table_price_id = :tablePriceId")
     fun deleteValueDetailsByPriceId(tablePriceId: Int)
}