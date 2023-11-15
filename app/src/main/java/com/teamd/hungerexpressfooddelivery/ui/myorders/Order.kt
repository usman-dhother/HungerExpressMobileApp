package com.teamd.hungerexpressfooddelivery.ui.myorders

data class Order(
    val _id: String,
    val user_id: String,
    val restaurant_id: String,
    val order_status: String,
    val delivery_address: String,
    val total_price: Double,
    val order_items: List<OrderItem>
)
