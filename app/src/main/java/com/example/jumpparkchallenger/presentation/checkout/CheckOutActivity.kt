package com.example.jumpparkchallenger.presentation.checkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.jumpparkchallenger.R
import com.example.jumpparkchallenger.databinding.ActivityCheckOutBinding
import com.example.jumpparkchallenger.domain.entities.Vehicle
import com.example.jumpparkchallenger.domain.entities.home.PaymentMethod
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Locale

class CheckOutActivity : AppCompatActivity() {

    private val viewModel : CheckOutViewModel by viewModel()
    private lateinit var binding: ActivityCheckOutBinding
    private lateinit var vehicle: Vehicle
    private lateinit var paymentMethods: List<PaymentMethod>
    private var totalValue = 0.0
    private val numberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))

    private val sdf = SimpleDateFormat("dd/MM/yyyy - HH:mm:ss", Locale("pt", "BR"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_out)
        binding = ActivityCheckOutBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel.getPaymentsMethod()

        viewModel.responsePaymentMethod.observe(this){ paymentMethods = it }

        initVariables()
        initListeners()
    }

    private fun initVariables() {
        vehicle = intent.getSerializableExtra("vehicle") as Vehicle

    }

    private fun initViews(result: Pair<Int, Double>) {
        binding.carTextView.text = "${vehicle.plate} - ${vehicle.model} / ${vehicle.color}"
        binding.checkinDateTextView.text = sdf.format(vehicle.date)
        binding.tableTextView.text = vehicle.price.priceType
        binding.totalValue.text = numberFormat.format(result.second)
        val hours = result.first / 60
        val minutes = result.first % 60
        binding.totalTimeTextView.text = String.format("%02d:%02d", hours, minutes)
    }
    private fun initListeners(){
        binding.checkoutButton.setOnClickListener { showCheckoutDialog() }
    }

    private fun calculateValue(){
        val result = viewModel.calculateValue(vehicle)
        totalValue = result.second
        initViews(result)
    }

    private fun showCheckoutDialog() {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.dialog_checkout, null)

        val txtTotalValue: TextView = dialogLayout.findViewById(R.id.txt_total_value)
        val radioGroupPaymentMethods: RadioGroup = dialogLayout.findViewById(R.id.radio_group_payment_methods)
        val btnPay: Button = dialogLayout.findViewById(R.id.btn_pay)

        txtTotalValue.text = totalValue.toString()

        for (paymentMethod in paymentMethods) {
            val radioButton = RadioButton(this)
            radioButton.text = paymentMethod.name
            radioGroupPaymentMethods.addView(radioButton)
        }

        builder.setView(dialogLayout)
        builder.setCancelable(false)

        val dialog = builder.create()

        btnPay.setOnClickListener {
            val selectedRadioButtonId = radioGroupPaymentMethods.checkedRadioButtonId

            if (selectedRadioButtonId != -1) {
                val selectedRadioButton = dialogLayout.findViewById<RadioButton>(selectedRadioButtonId)
                val paymentName = selectedRadioButton.text.toString()
                val paymentMethodSelected = paymentMethods.find { it.name == paymentName }
                checkout(paymentMethodSelected!!)
                finish()
                dialog.dismiss()
            } else {
                dialog.dismiss()
            }
        }

        dialog.show()
    }

    private fun checkout(paymentMethodSelected: PaymentMethod) {
        viewModel.checkOut(vehicle, totalValue, paymentMethodSelected)
    }

    override fun onResume() {
        super.onResume()
        calculateValue()
    }
}