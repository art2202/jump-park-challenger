package com.example.jumpparkchallenger.data.database.dao
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import androidx.room.OnConflictStrategy
import com.example.jumpparkchallenger.data.database.entity.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserEntity)

    @Query("SELECT * FROM user LIMIT 1")
    fun getUser(): UserEntity?

    @Delete
    fun deleteUser(user: UserEntity)

    @Query("DELETE FROM user")
    fun deleteAllUsers()
}
