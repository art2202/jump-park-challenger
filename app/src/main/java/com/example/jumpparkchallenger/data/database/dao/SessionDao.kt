package com.example.jumpparkchallenger.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jumpparkchallenger.data.database.entity.SessionEntity

@Dao
interface SessionDao {

    @Query("SELECT * FROM session LIMIT 1")
    fun getSession(): SessionEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSession(session: SessionEntity): Long


    @Query("DELETE FROM session")
    fun deleteAllSessions()
}
