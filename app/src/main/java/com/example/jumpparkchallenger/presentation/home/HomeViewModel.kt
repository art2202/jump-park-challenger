package com.example.jumpparkchallenger.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jumpparkchallenger.domain.entities.home.PaymentMethod
import com.example.jumpparkchallenger.domain.entities.home.Price
import com.example.jumpparkchallenger.domain.usecases.LoadHomeInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val loadHomeInfo: LoadHomeInfo) : ViewModel() {

    val responseHomeData = MutableLiveData<List<PaymentMethod>?>()
    fun loadInfo(){
        try {
            viewModelScope.launch(Dispatchers.IO) {
                val result = loadHomeInfo()
                responseHomeData.postValue(result)
            }
        }
        catch (t : Throwable){
            responseHomeData.postValue(null)
        }
    }
}