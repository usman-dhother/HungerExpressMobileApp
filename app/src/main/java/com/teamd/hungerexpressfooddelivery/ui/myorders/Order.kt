package com.teamd.hungerexpressfooddelivery.ui.myorders

data class Order(
    // Define your properties here
    val orderId: String,
    val itemName: String,
    val quantity: Int,
    val price: Double
)
