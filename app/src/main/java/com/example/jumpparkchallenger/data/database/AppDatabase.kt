package com.example.jumpparkchallenger.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jumpparkchallenger.core.App
import com.example.jumpparkchallenger.data.database.dao.EstablishmentDao
import com.example.jumpparkchallenger.data.database.dao.UserDao
import com.example.jumpparkchallenger.data.database.entity.EstablishmentEntity
import com.example.jumpparkchallenger.data.database.entity.UserEntity


@Database(
    entities = [
        UserEntity::class,
        EstablishmentEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao() : UserDao
    abstract fun establishmentDao() : EstablishmentDao

}