package com.example.jumpparkchallenger.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.jumpparkchallenger.databinding.FragmentHomeBinding
import com.example.jumpparkchallenger.domain.entities.home.PaymentMethod
import com.example.jumpparkchallenger.domain.entities.home.Price
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private val viewModel : HomeViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        viewModel.loadInfo()
        viewModel.responseHomeData.observe(viewLifecycleOwner){
            initView(it)
        }
        return binding!!.root
    }

    private fun initView(data: List<PaymentMethod>?) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}