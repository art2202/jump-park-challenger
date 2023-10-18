package com.example.jumpparkchallenger.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jumpparkchallenger.core.App
import com.example.jumpparkchallenger.data.database.dao.UserDao
import com.example.jumpparkchallenger.data.database.entity.UserEntity


@Database(
    entities = [
        UserEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {


    abstract fun userDao() : UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase (): AppDatabase? {
            if (this.INSTANCE != null) {
                return this.INSTANCE
            } else {
                synchronized(this) {
                    val instance = Room.databaseBuilder(
                        App.instance,
                        AppDatabase::class.java,
                        "jump-park-challenger")
                        .build()
                    this.INSTANCE = instance
                    return instance
                }
            }
        }
    }
}