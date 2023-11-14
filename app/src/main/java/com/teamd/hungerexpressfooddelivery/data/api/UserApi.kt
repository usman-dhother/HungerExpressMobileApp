package com.teamd.hungerexpressfooddelivery.data.api

import com.teamd.hungerexpressfooddelivery.data.model.ForgetPasswordRequest
import com.teamd.hungerexpressfooddelivery.data.model.ForgetPasswordResponse
import com.teamd.hungerexpressfooddelivery.data.model.RestaurantListRequestItem
import com.teamd.hungerexpressfooddelivery.data.model.SignInRequest
import com.teamd.hungerexpressfooddelivery.data.model.SignUpRequest
import com.teamd.hungerexpressfooddelivery.data.model.UserResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserApi {

    @POST("api/user/create")
    suspend fun signup(@Body signUpRequest: SignUpRequest): Response<UserResponse>

    @POST("api/user/login")
    suspend fun signin(@Body signInRequest: SignInRequest): Response<UserResponse>

    @GET("api/user/id/{id}")
    suspend fun getUserById(@Path("id") userId: String): Response<UserResponse>

    @PUT("api/user/update/{username}")
    suspend fun updateUserByUsername(@Path("username") username: String, @Body signUpRequest: SignUpRequest): Response<UserResponse>

    @POST("api/user/reset-password")
    suspend fun forgetPassword(@Body request: ForgetPasswordRequest): ResponseBody

}