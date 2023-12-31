package com.example.jumpparkchallenger.presentation.checkout

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jumpparkchallenger.domain.entities.CalculateValue
import com.example.jumpparkchallenger.domain.entities.Vehicle
import com.example.jumpparkchallenger.domain.entities.home.PaymentMethod
import com.example.jumpparkchallenger.domain.usecases.CheckOut
import com.example.jumpparkchallenger.domain.usecases.GetPaymentsMethod
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CheckOutViewModel(
    private val calculateValueUseCase: CalculateValue,
    private val getPaymentsMethodUseCase: GetPaymentsMethod,
    private val checkOutUseCase : CheckOut,
) : ViewModel() {

    val responsePaymentMethod = MutableLiveData<List<PaymentMethod>>()

    fun getPaymentsMethod(){
        viewModelScope.launch(Dispatchers.IO){
            val result = getPaymentsMethodUseCase()
            responsePaymentMethod.postValue(result)
        }
    }

    fun calculateValue(vehicle: Vehicle): Pair<Int, Double> {
        return calculateValueUseCase(vehicle)
    }

    fun checkOut(vehicle: Vehicle, totalValue: Double, paymentSelected: PaymentMethod) {
        viewModelScope.launch(Dispatchers.IO){
            paymentSelected.total += totalValue
            checkOutUseCase(vehicle, paymentSelected)
        }
    }
}