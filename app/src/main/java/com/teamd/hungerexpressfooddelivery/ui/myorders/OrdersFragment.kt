package com.teamd.hungerexpressfooddelivery.ui.myorders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
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
    private lateinit var spinnerOrderStatus: Spinner

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // First set of dummy order items
        val dummyOrderItems1 = listOf(
            OrderItem(
                _id = "6548fd71a74bb98a900dfa3d",
                restaurant_id = "65395f531d52eef241104b04",
                name = "HungerExpressAgent",
                description = "Test Description",
                price = 6.99,
                availability = "In stock",
                image_url = "data:image/jpeg;base64,..."
            )
            // Add more items if needed
        )

        // Second set of dummy order items
        val dummyOrderItems2 = listOf(
            OrderItem(
                _id = "65496f573b350695b148ff59",
                restaurant_id = "65395f861d52eef241104b06",
                name = "Cupcakes.",
                description = "Cupcakes are delightful, single-serving desserts...",
                price = 2.99,
                availability = "In stock",
                image_url = "data:image/jpeg;base64,..."
            ),
            OrderItem(
                _id = "654970413b350695b148ff61",
                restaurant_id = "65395f861d52eef241104b06",
                name = "Italian Pastries",
                description = "Italian pastries, known as 'pasticceria' in Italian...",
                price = 15.99,
                availability = "In stock",
                image_url = "data:image/jpeg;base64,..."
            )
        )


        // List of dummy orders
        val dummyOrders = listOf(
            Order(
                _id = "654a50ff82c00b41050ef754",
                user_id = "653dcd220371209567782a51",
                restaurant_id = "65395f531d52eef241104b04",
                order_status = "Placed",
                delivery_address = "Test, Pitta Reddy, 35 Tes, test, Poughkeepsie, 89898, Tenesse",
                total_price = 6.99,
                order_items = dummyOrderItems1
            ),
            Order(
                _id = "65531bcc610d63a4f5948725",
                user_id = "652aad724c9278c07baab04b",
                restaurant_id = "65395f861d52eef241104b06",
                order_status = "Placed",
                delivery_address = "sd, asdas, das, das, das, dasdsa, South Carolina",
                total_price = 20.1188,
                order_items = dummyOrderItems2
            )
        )

        val view = inflater.inflate(R.layout.fragment_orders, container, false)
        recyclerView = view.findViewById(R.id.recyclerViewOrders)
        recyclerView.layoutManager = LinearLayoutManager(context)
        orderAdapter = OrderAdapter(dummyOrders)
        recyclerView.adapter = orderAdapter
        // Initialize Spinner
        spinnerOrderStatus = view.findViewById(R.id.spinnerOrderStatus)
        setupSpinner()
        return view
    }

    private fun setupSpinner() {
        val statuses = arrayOf("Placed", "In Progress", "Completed", "Cancelled")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, statuses)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerOrderStatus.adapter = adapter

        spinnerOrderStatus.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedStatus = parent.getItemAtPosition(position) as String
                // Handle the selection event, e.g., filter the orders based on status
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Optional: Handle the case where nothing is selected
            }
        }
    }
}