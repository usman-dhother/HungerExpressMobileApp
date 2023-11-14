package com.teamd.hungerexpressfooddelivery.data.model

data class MenuItemRestaurantResponse(
    val __v: Int,
    val _id: String,
    val allergy_info: List<Any>,
    val availability: String,
    val description: String,
    val image_url: String,
    val name: String,
    val price: Double,
    val restaurant_id: String,
    val error:String? = null
)