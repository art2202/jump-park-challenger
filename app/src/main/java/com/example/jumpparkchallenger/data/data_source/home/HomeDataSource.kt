package com.example.jumpparkchallenger.data.data_source.home

import com.example.jumpparkchallenger.data.models.HomeData

interface HomeDataSource {

    suspend fun getData(userId : Int, establishmentId: Int): HomeData
}