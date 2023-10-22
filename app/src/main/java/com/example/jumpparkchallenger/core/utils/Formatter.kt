package com.example.jumpparkchallenger.core.utils

import java.text.NumberFormat
import java.util.Locale
object Formatter {

    fun format(double: Double?): String? {
        val format = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
        return format.format(double)
    }
}

