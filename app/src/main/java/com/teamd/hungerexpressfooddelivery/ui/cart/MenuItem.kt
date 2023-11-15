package com.teamd.hungerexpressfooddelivery.ui.cart

data class MenuItem(
    val name: String,
    val description: String,
    val price: Double,
    val availability: String,
    val allergyInfo: String
    // Add other fields as needed
)