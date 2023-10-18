package com.example.jumpparkchallenger.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import com.example.jumpparkchallenger.data.database.entity.EstablishmentEntity

@Dao
interface EstablishmentDao {

    @Insert
    suspend fun insertEstablishment(establishment: EstablishmentEntity): Long

    @Query("SELECT * FROM establishment LIMIT 1")
    suspend fun getEstablishment(): EstablishmentEntity?

    @Delete
    suspend fun deleteEstablishment(establishment: EstablishmentEntity)

    // Se você preferir deletar todos os estabelecimentos sem passar um específico:
    @Query("DELETE FROM establishment")
    suspend fun deleteAllEstablishments()
}
