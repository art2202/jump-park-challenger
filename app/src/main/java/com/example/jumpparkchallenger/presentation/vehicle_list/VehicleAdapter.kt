package com.example.jumpparkchallenger.presentation.vehicle_list

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.jumpparkchallenger.R
import com.example.jumpparkchallenger.domain.entities.Vehicle
import com.example.jumpparkchallenger.presentation.ColorEnum
import java.text.SimpleDateFormat
import java.util.Locale

class VehicleAdapter(private val vehicles: List<Vehicle>, private val openCheckOut: (vehicle : Vehicle) -> Unit)
    : RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder>() {

    class VehicleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val infoCarTextView: TextView = itemView.findViewById(R.id.infoCarTextView)
        val vehicleCardView : CardView = itemView.findViewById(R.id.card_view_vehicle)
        val dateTextView : TextView = itemView.findViewById(R.id.dateTextView)
        val priceTextView : TextView = itemView.findViewById(R.id.priceTextView)
        val colorView : View = itemView.findViewById(R.id.colorView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.vehicle_item, parent, false)
        return VehicleViewHolder(view)
    }

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {

        val sdf = SimpleDateFormat("dd/MM/yyyy - HH:mm:ss", Locale("pt", "BR"))

        val vehicle = vehicles[position]
        val text = "${vehicle.plate} - ${vehicle.model} / ${vehicle.color}"
        holder.infoCarTextView.text = text


        val hexValue = ColorEnum.valueOf(vehicle.color.uppercase()).hexValue

        ColorEnum.getHexValueByName(vehicle.color)
        holder.colorView.setBackgroundColor(Color.parseColor(hexValue))
        holder.dateTextView.text = sdf.format(vehicle.date)
        holder.priceTextView.text = vehicle.price.priceType

        holder.vehicleCardView.setOnClickListener { openCheckOut(vehicle) }
    }

    override fun getItemCount(): Int = vehicles.size
}
