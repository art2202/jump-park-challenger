package com.example.jumpparkchallenger.domain.entities

import com.example.jumpparkchallenger.domain.entities.home.Price
import java.io.Serializable
import java.util.Date

data class Vehicle(
    val plate : String,
    val model : String,
    val color : String,
    val price: Price,
    val date : Date
) : Serializable