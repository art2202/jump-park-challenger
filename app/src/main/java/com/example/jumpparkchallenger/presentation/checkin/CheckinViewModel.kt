package com.example.jumpparkchallenger.presentation.checkin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jumpparkchallenger.domain.entities.home.Price
import com.example.jumpparkchallenger.domain.usecases.GetPrices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CheckInViewModel(private val getPricesUseCase: GetPrices) : ViewModel() {

    val priceLiveData = MutableLiveData<List<Price>>()
    fun getPrices(){
        try {
            viewModelScope.launch(Dispatchers.IO) {
                val result = getPricesUseCase()
                priceLiveData.postValue(result)
            }
        }
        catch (t : Throwable){
            priceLiveData.postValue(null)
        }
    }
}