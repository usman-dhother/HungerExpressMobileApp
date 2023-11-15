package com.teamd.hungerexpressfooddelivery.ui.cart

data class MenuItem(
    val name: String,
    val description: String,
    val price: Double,
    val availability: String,
    val allergyInfo: String,
    var quantity: Int // Added quantity field
)
