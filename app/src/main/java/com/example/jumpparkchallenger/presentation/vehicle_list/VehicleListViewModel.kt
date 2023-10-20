package com.example.jumpparkchallenger.presentation.vehicle_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jumpparkchallenger.domain.entities.Vehicle
import com.example.jumpparkchallenger.domain.usecases.VehicleList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VehicleListViewModel(private val vehicleListUseCase: VehicleList) : ViewModel() {

  val responseVehicleList = MutableLiveData<List<Vehicle>>()

    fun getVehicleList(){
        viewModelScope.launch(Dispatchers.IO){
            val result = vehicleListUseCase()
            responseVehicleList.postValue(result)
        }
    }
}