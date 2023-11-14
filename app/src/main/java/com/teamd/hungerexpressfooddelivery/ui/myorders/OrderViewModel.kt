package com.teamd.hungerexpressfooddelivery.ui.myorders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teamd.hungerexpressfooddelivery.ui.myorders.Order


class OrderViewModel : ViewModel() {

    private val _orderList = MutableLiveData<List<Order>>()
    val orderList: LiveData<List<Order>> get() = _orderList

    // Populate orderList with data from your repository or API

    fun cancelOrder(orderId: String) {
        // Implement the logic to cancel the order (update the status)
        // You might need to notify your repository or API to update the order status.
    }
}
