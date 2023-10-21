package com.example.jumpparkchallenger.presentation.checkin

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.jumpparkchallenger.R
import com.example.jumpparkchallenger.databinding.ActivityCheckInBinding
import com.example.jumpparkchallenger.domain.entities.Vehicle
import com.example.jumpparkchallenger.domain.entities.home.Price
import com.example.jumpparkchallenger.presentation.ColorEnum
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Date
import java.util.regex.Pattern

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
        setColorSpinner()
    }

    private fun setColorSpinner(){
        val spinnerData = arrayListOf("selecione...")
        spinnerData.addAll(ColorEnum.getNomeList())
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerData)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerColor.adapter = adapter

    }
    private fun cleanForm() {
        binding.editTextPlate.text = ""
        binding.editTextModel.text = ""
        binding.spinnerColor.setSelection(0)
        binding.spinner.setSelection(0)
    }

    private fun initListeners() {
        binding.buttonConfirm.setOnClickListener {
            if(validForm()) {
                val color = binding.spinnerColor.selectedItem as String
                val vehicle = Vehicle(
                    binding.editTextPlate.text,
                    binding.editTextModel.text,
                    color,
                    viewModel.prices[binding.spinner.selectedItemPosition - 1],
                    Date()
                )
                viewModel.save(vehicle)
            }
        }
    }

    private fun validForm(): Boolean {
        if(!validPlate(binding.editTextPlate.text)){
            binding.editTextPlate.error = "Insira uma placa válida"
            return false
        }

        if(binding.editTextModel.text.isEmpty()){
            binding.editTextModel.error = "Insira um modelo"
            return false
        }

        val color = binding.spinnerColor.selectedItem as String
        if(color == "selecione...") {
            binding.spinnerColor.performClick()
            Toast.makeText(this, "Selecione uma cor", Toast.LENGTH_SHORT).show()
            return false
        }

        val table = binding.spinner.selectedItem as String
        if(table == "selecione...") {
            binding.spinner.performClick()
            Toast.makeText(this, "Selecione uma tabela", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun validPlate(plate: String): Boolean {
        if (plate.isEmpty() || plate.length != 7) return false
        val regex = "[A-Za-z][A-Za-z][A-Za-z][0-9][A-Za-z0-9_-][0-9][0-9]"
        val pattern = Pattern.compile(regex)
        val matcher = pattern.matcher(plate)
        return matcher.matches()
    }

    private fun setSpinner(prices: List<Price>) {
        val spinnerData = arrayListOf("selecione...")
        val list = prices.map { it.priceType ?: "" }
        spinnerData.addAll(list)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerData)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter
    }

}