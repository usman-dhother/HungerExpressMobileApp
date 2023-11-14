package com.teamd.hungerexpressfooddelivery.data.model

data class UserResponse(
    val __v: Int,
    val _id: String,
    val email: String,
    val first_name: String,
    val last_name: String,
    val password_hash: String,
    val user_type: String,
    val username: String
)