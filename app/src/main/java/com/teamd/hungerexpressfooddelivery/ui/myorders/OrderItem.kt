package com.teamd.hungerexpressfooddelivery.ui.myorders

data class OrderItem(
    val _id: String,
    val restaurant_id: String,
    val name: String,
    val description: String,
    val price: Double,
    val availability: String,
    val image_url: String
)
