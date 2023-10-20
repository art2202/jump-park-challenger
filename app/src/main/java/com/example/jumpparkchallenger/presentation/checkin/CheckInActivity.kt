package com.example.jumpparkchallenger.presentation.checkin

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.jumpparkchallenger.R
import com.example.jumpparkchallenger.databinding.ActivityCheckInBinding
import com.example.jumpparkchallenger.domain.entities.Vehicle
import com.example.jumpparkchallenger.domain.entities.home.Price
import org.koin.androidx.viewmodel.ext.android.viewModel

class CheckInActivity : AppCompatActivity() {

    private val viewModel : CheckInViewModel by viewModel()
    private lateinit var binding : ActivityCheckInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_in)

        binding = ActivityCheckInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getPrices()
        viewModel.saveVehicleLiveData.observe(this){result ->
            result?.let {

                val toastText = if(it) "Veículo salvo com sucesso" else "Não foi possivel salvar"

                Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show()
                cleanForm()
            }
        }
        viewModel.priceLiveData.observe(this){
            setSpinner(it ?: listOf())
        }
        initListeners()
    }

    private fun cleanForm() {
        binding.editTextPlate.setText("")
        binding.editTextModel.setText("")
        binding.editTextColor.setText("")
        binding.spinner.setSelection(0)
    }

    private fun initListeners() {
        binding.buttonConfirm.setOnClickListener {

            val vehicle = Vehicle(
                binding.editTextPlate.text.toString(),
                binding.editTextModel.text.toString(),
                binding.editTextColor.text.toString(),
                viewModel.prices[binding.spinner.selectedItemPosition]
            )
            viewModel.save(vehicle)
//            Toast.makeText(this, "${}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setSpinner(prices: List<Price>) {
        val list = prices.map { it.priceType ?: "" }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter
    }

}