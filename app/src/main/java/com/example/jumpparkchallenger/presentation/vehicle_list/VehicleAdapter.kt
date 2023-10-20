package com.example.jumpparkchallenger.presentation.vehicle_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jumpparkchallenger.R
import com.example.jumpparkchallenger.domain.entities.Vehicle

class VehicleAdapter(private val vehicles: List<Vehicle>) : RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder>() {

    // ViewHolder para representar cada item do RecyclerView
    class VehicleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val plateTextView: TextView = itemView.findViewById(R.id.plateTextView)
        val modelTextView: TextView = itemView.findViewById(R.id.modelTextView)
        val colorTextView: TextView = itemView.findViewById(R.id.colorTextView)
        // Adicione outros campos aqui conforme necessário
    }

    // Infla o layout do item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.vehicle_item, parent, false)
        return VehicleViewHolder(view)
    }

    // Vincula os dados ao ViewHolder
    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        val vehicle = vehicles[position]
        holder.plateTextView.text = vehicle.plate
        holder.modelTextView.text = vehicle.model
        holder.colorTextView.text = vehicle.color
        // Faça a vinculação de outros campos aqui conforme necessário
    }

    // Retorna a quantidade de itens na lista
    override fun getItemCount(): Int = vehicles.size
}
