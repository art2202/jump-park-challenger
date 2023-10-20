package com.example.jumpparkchallenger.presentation.vehicle_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class VehicleListViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Cars List Fragment"
    }
    val text: LiveData<String> = _text
}