package com.example.jumpparkchallenger.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import com.example.jumpparkchallenger.data.database.entity.EstablishmentEntity

@Dao
interface EstablishmentDao {

    @Insert
    fun insertEstablishment(establishment: EstablishmentEntity): Long

    @Query("SELECT * FROM establishment LIMIT 1")
    fun getEstablishment(): EstablishmentEntity?

    @Delete
    fun deleteEstablishment(establishment: EstablishmentEntity)

    @Query("DELETE FROM establishment")
    fun deleteAllEstablishments()
}
