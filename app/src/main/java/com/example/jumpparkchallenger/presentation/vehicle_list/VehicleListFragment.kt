package com.example.jumpparkchallenger.presentation.vehicle_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jumpparkchallenger.databinding.FragmentVehicleListBinding
import com.example.jumpparkchallenger.domain.entities.Vehicle
import org.koin.androidx.viewmodel.ext.android.viewModel

class VehicleListFragment : Fragment() {

    private var binding: FragmentVehicleListBinding? = null
    private val viewModel : VehicleListViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentVehicleListBinding.inflate(inflater, container, false)

        viewModel.responseVehicleList.observe(viewLifecycleOwner){vehicleList ->
            initView(vehicleList)
        }

        return binding!!.root
    }

    private fun initView(vehicleList: List<Vehicle>?) {
        binding?.recyclerViewVehicles?.layoutManager = LinearLayoutManager(requireContext())
        val adapter = VehicleAdapter(vehicleList ?: listOf())
        binding?.recyclerViewVehicles?.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onResume() {
        super.onResume()
        viewModel.getVehicleList()
    }
}