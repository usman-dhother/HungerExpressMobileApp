package com.teamd.hungerexpressfooddelivery.data.model

data class SignUpRequest(
    val email: String,
    val firstName: String,
    val lastName: String,
    val password: String
)