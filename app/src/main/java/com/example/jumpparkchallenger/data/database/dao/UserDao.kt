package com.example.jumpparkchallenger.data.database.dao
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import com.example.jumpparkchallenger.data.database.entity.UserEntity

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: UserEntity): Long

    @Query("SELECT * FROM user LIMIT 1")
    suspend fun getUser(): UserEntity?

    @Delete
    suspend fun deleteUser(user: UserEntity)

    // Se você preferir deletar todos os usuários sem passar um específico:
    @Query("DELETE FROM user")
    suspend fun deleteAllUsers()
}
