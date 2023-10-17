package com.example.jumpparkchallenger.presentation.cars_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CarsListViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Cars List Fragment"
    }
    val text: LiveData<String> = _text
}