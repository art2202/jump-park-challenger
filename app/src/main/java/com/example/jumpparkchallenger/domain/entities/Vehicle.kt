package com.example.jumpparkchallenger.domain.entities

import com.example.jumpparkchallenger.domain.entities.home.Price
import java.io.Serializable

data class Vehicle(
    val plate : String,
    val model : String,
    val color : String, //todo fazer ENUM ou um array e colocar num spinner para padronizar cores
    val price: Price
) : Serializable