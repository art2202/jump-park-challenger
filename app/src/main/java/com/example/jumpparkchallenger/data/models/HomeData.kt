package com.example.jumpparkchallenger.data.models

import com.google.gson.annotations.SerializedName

class HomeData(
    @SerializedName("paymentMethods")
    val paymentMethodsResponseData : List<PaymentMethodosResponseData?>,
    @SerializedName("prices")
    val pricesResponseData : List<PricesResponseData?>
)