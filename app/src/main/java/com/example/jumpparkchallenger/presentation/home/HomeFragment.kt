package com.example.jumpparkchallenger.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jumpparkchallenger.core.utils.Formatter
import com.example.jumpparkchallenger.databinding.FragmentHomeBinding
import com.example.jumpparkchallenger.domain.entities.home.HomeInfos
import com.example.jumpparkchallenger.domain.entities.home.PaymentMethod
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private val viewModel : HomeViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        viewModel.responseHomeData.observe(viewLifecycleOwner){
            if (it != null) {
                shimmerStart(false)
                initView(it)

            }
        }
        return binding!!.root
    }

    private fun initView(data: HomeInfos?) {
        makeRecyclerView(data?.paymentsMethods)
        binding?.totalCarsTextView?.text = "${data?.totalVehicle}/${data?.totalVacancies}"
        binding?.totalAmountTextView?.text = Formatter.format(data?.paymentsMethods?.sumOf { it.total })
    }

    private fun makeRecyclerView(data: List<PaymentMethod>?){
        binding?.paymentRecyclerView?.layoutManager = LinearLayoutManager(requireContext())
        val adapter = PaymentMethodAdapter(data ?: listOf())
        binding?.paymentRecyclerView?.adapter = adapter
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onResume() {
        super.onResume()
        shimmerStart(true)
        viewModel.loadInfo()
    }

    private fun shimmerStart(boolean : Boolean){
        binding?.totalPaymentsReceiveTextView?.isVisible = !boolean
        binding?.paymentRecyclerView?.isVisible = !boolean
        binding?.shimmerView?.showShimmer(boolean)
        binding?.shimmerView?.isVisible = boolean
        binding?.vehicleCoutLinearLayout?.isVisible = !boolean
        binding?.totalLinearLayout?.isVisible = !boolean
    }
}