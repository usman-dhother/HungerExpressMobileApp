package com.teamd.hungerexpressfooddelivery.ui.myorders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.teamd.hungerexpressfooddelivery.R



import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.teamd.hungerexpressfooddelivery.databinding.FragmentOrdersBinding
import com.teamd.hungerexpressfooddelivery.ui.myorders.OrderAdapter

class OrdersFragment : Fragment(), OrderAdapter.OnOrderClickListener {

    private lateinit var binding: FragmentOrdersBinding
    private lateinit var orderAdapter: OrderAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrdersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        orderAdapter = OrderAdapter(this)
        binding.recyclerViewOrders.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewOrders.adapter = orderAdapter

        // Assuming you have a ViewModel to provide order data
        // val ordersViewModel = ViewModelProvider(this).get(OrdersViewModel::class.java)

        // Observe ordersLiveData from ViewModel and submit the list to the adapter
        // ordersViewModel.ordersLiveData.observe(viewLifecycleOwner) { orders ->
        //     orderAdapter.submitList(orders)
        // }
    }

    override fun onOrderCancelClick(orderItem: Order) {
        // Handle order cancellation logic here
    }
}

