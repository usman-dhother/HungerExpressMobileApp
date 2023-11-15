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
import androidx.recyclerview.widget.RecyclerView
import com.teamd.hungerexpressfooddelivery.databinding.FragmentOrdersBinding
import com.teamd.hungerexpressfooddelivery.ui.myorders.OrderAdapter

class OrdersFragment : Fragment() {

    private lateinit var orderAdapter: OrderAdapter
    private lateinit var recyclerView: RecyclerView



//    // Initialize the adapter with dummy data
//    orderAdapter = OrderAdapter(dummyOrders)
//    binding.recyclerViewOrders.layoutManager = LinearLayoutManager(requireContext())
//    binding.recyclerViewOrders.adapter = orderAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Create dummy data
        val dummyOrders = listOf(
            Order(orderId = "1", itemName = "Pizza", quantity = 2, price = 15.99),
            Order(orderId = "2", itemName = "Burger", quantity = 1, price = 8.99),
            Order(orderId = "3", itemName = "Pasta", quantity = 3, price = 12.50)
        )

        val view = inflater.inflate(R.layout.fragment_orders, container, false)
        recyclerView = view.findViewById(R.id.recyclerViewOrders)
        recyclerView.layoutManager = LinearLayoutManager(context)
        orderAdapter = OrderAdapter(dummyOrders)
        recyclerView.adapter = orderAdapter
        return view
    }
}
