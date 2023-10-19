package com.example.jumpparkchallenger.data.models.home

import com.google.gson.annotations.SerializedName

class PaymentMethodosResponseData(
    @SerializedName("establishmentPaymentMethodId")
    val id : Int?,
    @SerializedName("paymentMethodName")
    val name : String?
) {
}