package com.example.jumpparkchallenger.presentation.checkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jumpparkchallenger.R
import com.example.jumpparkchallenger.domain.entities.Vehicle

class CheckOutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_out)

        val vehicle = intent.getSerializableExtra("vehicle") as Vehicle?
        vehicle?.let {

        }
    }
}