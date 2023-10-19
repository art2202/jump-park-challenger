package com.example.jumpparkchallenger.data.models.home

import com.google.gson.annotations.SerializedName

class HomeData(
    @SerializedName("paymentMethods")
    val paymentMethodsResponseData : List<PaymentMethodosResponseData?>,
    @SerializedName("prices")
    val pricesResponseData : List<PricesResponseData?>
)