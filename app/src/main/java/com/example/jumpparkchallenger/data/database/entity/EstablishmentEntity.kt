package com.example.jumpparkchallenger.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "establishment")
data class EstablishmentEntity(
    @PrimaryKey
    val id: Int,
    val name: String?,
    @ColumnInfo(name = "vacancies")
    val vacanciesMarks: Int
)
