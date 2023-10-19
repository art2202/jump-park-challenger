package com.example.jumpparkchallenger.presentation.checkin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jumpparkchallenger.R
import com.example.jumpparkchallenger.domain.entities.home.Price
import org.koin.androidx.viewmodel.ext.android.viewModel

class CheckInActivity : AppCompatActivity() {

    private val viewModel : CheckInViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_in)

        viewModel.getPrices()
        viewModel.priceLiveData.observe(this){
            setSpinner(it)
        }

    }

    private fun setSpinner(prices: List<Price>) {

    }
}