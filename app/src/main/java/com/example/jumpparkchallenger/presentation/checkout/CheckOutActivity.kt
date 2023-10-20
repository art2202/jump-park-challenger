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

class CheckOutActivity : AppCompatActivity() {

    private val viewModel : CheckOutViewModel by viewModel()
    private lateinit var binding: ActivityCheckOutBinding
    private lateinit var vehicle: Vehicle
    private lateinit var paymentMethods: List<PaymentMethod>
    private var totalValue = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_out)
        binding = ActivityCheckOutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getPaymentsMethod()

        viewModel.responsePaymentMethod.observe(this){ paymentMethods = it }

        initVariables()
        initViews()
        initListeners()
    }

    private fun initVariables() {
        vehicle = intent.getSerializableExtra("vehicle") as Vehicle

    }

    private fun initViews(){
        binding.plateTextView.text = vehicle.plate
        binding.modelTextView.text = vehicle.model
        binding.colorTextView.text = vehicle.color

    }
    private fun initListeners(){
        binding.checkoutButton.setOnClickListener {
            showCheckoutDialog()
//            pegar o valor pago, adicionar na tabela paymentMethod e garantir q ela n seja 0 toda
//            vez que abrir, e excluir o carro do banco de dados
        }
    }

    private fun calculateValue(){
        val result = viewModel.calculateValue(vehicle)
        totalValue = result.second
        binding.totalTextView.text = "Tempo: ${result.first} \n Valor: R$${result.second}"
    }

    private fun showCheckoutDialog() {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.dialog_checkout, null)

        val txtTotalValue: TextView = dialogLayout.findViewById(R.id.txt_total_value)
        val radioGroupPaymentMethods: RadioGroup = dialogLayout.findViewById(R.id.radio_group_payment_methods)
        val btnPay: Button = dialogLayout.findViewById(R.id.btn_pay)

        txtTotalValue.text = totalValue.toString()

        // Supondo que você tenha uma lista chamada paymentMethods
        for (paymentMethod in paymentMethods) {
            val radioButton = RadioButton(this)
            radioButton.text = paymentMethod.name // Ajuste de acordo com sua classe
            radioGroupPaymentMethods.addView(radioButton)
        }

        builder.setView(dialogLayout)
        builder.setCancelable(false)

        val dialog = builder.create()

        btnPay.setOnClickListener {
            // Implemente a lógica de pagamento aqui

            dialog.dismiss() // Feche o diálogo quando o pagamento estiver concluído
        }

        dialog.show()
    }

    override fun onResume() {
        super.onResume()
        calculateValue()
    }
}