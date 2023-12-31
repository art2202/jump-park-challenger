package com.example.jumpparkchallenger.presentation.home
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jumpparkchallenger.R
import com.example.jumpparkchallenger.core.utils.Formatter
import com.example.jumpparkchallenger.domain.entities.home.PaymentMethod
import java.text.NumberFormat
import java.util.Locale

class PaymentMethodAdapter(private val paymentMethods: List<PaymentMethod>) : RecyclerView.Adapter<PaymentMethodAdapter.PaymentMethodViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentMethodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.payment_item, parent, false)
        return PaymentMethodViewHolder(view)
    }

    override fun onBindViewHolder(holder: PaymentMethodViewHolder, position: Int) {
        val paymentMethod = paymentMethods[position]


        when(paymentMethod.name){
            "Dinheiro" -> { holder.paymentIcon.setImageResource(R.drawable.ic_money) }
            "Crédito" -> { holder.paymentIcon.setImageResource(R.drawable.ic_card) }
            "Débito" -> { holder.paymentIcon.setImageResource(R.drawable.ic_card) }
            "Boleto" -> { holder.paymentIcon.setImageResource(R.drawable.ic_boleto) }
            "Pix" -> { holder.paymentIcon.setImageResource(R.drawable.ic_pix) }
            else -> {}
        }

        holder.paymentTitle.text = paymentMethod.name
        holder.paymentValue.text = Formatter.format(paymentMethod.total)
    }

    override fun getItemCount(): Int {
        return paymentMethods.size
    }

    inner class PaymentMethodViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val paymentIcon: ImageView = view.findViewById(R.id.paymentIcon)
        val paymentTitle: TextView = view.findViewById(R.id.paymentTitle)
        val paymentValue: TextView = view.findViewById(R.id.paymentValue)
    }
}
