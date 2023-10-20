package com.example.jumpparkchallenger.presentation.checkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jumpparkchallenger.R
import com.example.jumpparkchallenger.databinding.ActivityCheckOutBinding
import com.example.jumpparkchallenger.domain.entities.Vehicle
import org.koin.androidx.viewmodel.ext.android.viewModel

class CheckOutActivity : AppCompatActivity() {

    private val viewModel : CheckOutViewModel by viewModel()
    private lateinit var binding: ActivityCheckOutBinding
    private lateinit var vehicle: Vehicle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_out)
        binding = ActivityCheckOutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vehicle = intent.getSerializableExtra("vehicle") as Vehicle
        vehicle.let {
            binding.plateTextView.text = it.plate
            binding.modelTextView.text = it.model
            binding.colorTextView.text = it.color
        }
    }

    private fun calculateValue(){
        val result = viewModel.calculateValue(vehicle)
        binding.totalTextView.text = "Tempo: ${result.first} \n Valor: R$${result.second}"
    }

    override fun onResume() {
        super.onResume()
        calculateValue()
    }
}