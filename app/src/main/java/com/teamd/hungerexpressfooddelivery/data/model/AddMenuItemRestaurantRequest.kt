package com.teamd.hungerexpressfooddelivery.data.model

data class AddMenuItemRestaurantRequest(
    val availability: String,
    val description: String,
    val image_url: String,
    val name: String,
    val price: Float,
    val restaurant_id: String
)
