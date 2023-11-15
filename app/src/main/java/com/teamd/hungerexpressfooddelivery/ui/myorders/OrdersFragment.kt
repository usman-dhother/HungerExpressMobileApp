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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Stub data
        val dummyOrderItems = listOf(
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

        val dummyOrders = listOf(
            Order(
                _id = "654a50ff82c00b41050ef754",
                user_id = "653dcd220371209567782a51",
                restaurant_id = "65395f531d52eef241104b04",
                order_status = "Placed",
                delivery_address = "Test, Pitta Reddy, 35 Tes, test, Poughkeepsie, 89898, Tenesse",
                total_price = 6.99,
                order_items = dummyOrderItems
            )
            // Add more orders if needed
        )

        val view = inflater.inflate(R.layout.fragment_orders, container, false)
        recyclerView = view.findViewById(R.id.recyclerViewOrders)
        recyclerView.layoutManager = LinearLayoutManager(context)
        orderAdapter = OrderAdapter(dummyOrders)
        recyclerView.adapter = orderAdapter
        return view
    }
}