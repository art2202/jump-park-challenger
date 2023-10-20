package com.example.jumpparkchallenger.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jumpparkchallenger.data.database.dao.EstablishmentDao
import com.example.jumpparkchallenger.data.database.dao.PaymentMethodDao
import com.example.jumpparkchallenger.data.database.dao.PriceDao
import com.example.jumpparkchallenger.data.database.dao.UserDao
import com.example.jumpparkchallenger.data.database.dao.ValueDetailDao
import com.example.jumpparkchallenger.data.database.dao.VehicleDao
import com.example.jumpparkchallenger.data.database.entity.EstablishmentEntity
import com.example.jumpparkchallenger.data.database.entity.PaymentMethodEntity
import com.example.jumpparkchallenger.data.database.entity.PriceEntity
import com.example.jumpparkchallenger.data.database.entity.UserEntity
import com.example.jumpparkchallenger.data.database.entity.ValueDetailEntity
import com.example.jumpparkchallenger.data.database.entity.VehicleEntity


@Database(
    entities = [
        UserEntity::class,
        EstablishmentEntity::class,
        PriceEntity::class,
        PaymentMethodEntity::class,
        ValueDetailEntity::class,
        VehicleEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao() : UserDao
    abstract fun establishmentDao() : EstablishmentDao

    abstract fun PaymentMethodDao() : PaymentMethodDao

    abstract fun PriceDao() : PriceDao

    abstract fun ValueDetailDao() : ValueDetailDao

    abstract fun vehicleDao() : VehicleDao

}