package com.example.jumpparkchallenger.presentation.checkin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jumpparkchallenger.domain.entities.Vehicle
import com.example.jumpparkchallenger.domain.entities.home.Price
import com.example.jumpparkchallenger.domain.usecases.GetPrices
import com.example.jumpparkchallenger.domain.usecases.SaveVehicle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CheckInViewModel(private val getPricesUseCase: GetPrices, private val saveVehicleUseCase : SaveVehicle) : ViewModel() {

    val priceLiveData = MutableLiveData<List<Price>?>()
    var prices : List<Price> = arrayListOf()

    val saveVehicleLiveData = MutableLiveData<Boolean?>()

    fun getPrices(){
        try {
            viewModelScope.launch(Dispatchers.IO) {
                val result = getPricesUseCase()
                prices = result
                priceLiveData.postValue(result)
            }
        }
        catch (t : Throwable){
            priceLiveData.postValue(null)
        }
    }

    fun save(vehicle: Vehicle) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                val result = saveVehicleUseCase(vehicle)
                saveVehicleLiveData.postValue(result)
            }
        }
        catch (t : Throwable){
            saveVehicleLiveData.postValue(null)
        }
    }

}