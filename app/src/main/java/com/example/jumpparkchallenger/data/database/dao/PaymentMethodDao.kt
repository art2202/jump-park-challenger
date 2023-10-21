package com.example.jumpparkchallenger.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jumpparkchallenger.data.database.entity.PaymentMethodEntity

@Dao
interface PaymentMethodDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPaymentMethod(paymentMethod: PaymentMethodEntity)

    @Query("SELECT * FROM payment_method")
    fun getAll() : List<PaymentMethodEntity>

    @Query("SELECT * FROM payment_method WHERE id = :id")
    fun getPaymentMethod(id: Int): PaymentMethodEntity?

    @Delete
    fun deletePaymentMethod(paymentMethod: PaymentMethodEntity)

    @Query("DELETE FROM payment_method")
    fun deleteAllPaymentMethods()

    @Query("UPDATE payment_method SET total = :newTotal WHERE id = :id")
    fun updateTotalById(id: Int, newTotal: Double)
}