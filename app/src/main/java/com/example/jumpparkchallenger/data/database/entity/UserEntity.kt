package com.example.jumpparkchallenger.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String?,
    val email: String?
)