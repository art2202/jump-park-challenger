package com.example.jumpparkchallenger.presentation.vehicle_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.jumpparkchallenger.databinding.FragmentVehicleListBinding

class VehicleListFragment : Fragment() {

    private var binding: FragmentVehicleListBinding? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentVehicleListBinding.inflate(inflater, container, false)

        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}