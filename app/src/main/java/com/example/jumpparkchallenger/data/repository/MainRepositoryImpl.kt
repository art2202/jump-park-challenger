package com.example.jumpparkchallenger.data.repository

import com.example.jumpparkchallenger.data.data_source.main.MainDataSource
import com.example.jumpparkchallenger.data.data_source.main.MainLocalDataSource
import com.example.jumpparkchallenger.data.data_source.vehicle_list.VehicleListDataSource
import com.example.jumpparkchallenger.domain.repository.MainRepository

class MainRepositoryImpl(
    private val mainDataSource: MainDataSource,
    private val mainLocalDataSource: MainLocalDataSource,
    private val vehicleListDataSource: VehicleListDataSource
) : MainRepository {
    override suspend fun logout(): Boolean {
        val userEntity = mainLocalDataSource.getUser()
        val establishmentEntity = mainLocalDataSource.getEstablishment()
        val sessionEntity = mainLocalDataSource.getSession()
        val result =  mainDataSource.logout(userEntity?.id!!, establishmentEntity?.id!!, sessionEntity?.id!!)

        return if(result){
            deleteData()
            true
        } else{
            false
        }
    }

    override suspend fun getUser(): String {
        val userEntity = mainLocalDataSource.getUser()
        return userEntity?.email ?: ""
    }

    override suspend fun getVacancies(): Int {
        val totalVacancies = mainLocalDataSource.getEstablishment()?.vacanciesMarks ?: 0
        val totalVehicles = vehicleListDataSource.getAllVehicles().size
        return totalVacancies - totalVehicles
    }

    private suspend fun deleteData() {
        mainLocalDataSource.clearDatabase()
    }
}