package com.teamd.hungerexpressfooddelivery.data.api

import com.teamd.hungerexpressfooddelivery.data.model.AddMenuItemRestaurantRequest
import com.teamd.hungerexpressfooddelivery.data.model.MenuItemRestaurantResponse
import com.teamd.hungerexpressfooddelivery.data.model.RestaurantListRequestItem
import com.teamd.hungerexpressfooddelivery.data.model.UpdateRestaurant
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RestaurantApi {


    @GET("api/restaurant")
    suspend fun restaurantList(): Response<List<RestaurantListRequestItem>>

    @POST("api/restaurant/update/{id}")
    suspend fun updateRestaurantById(@Path("id") id: String, @Body updateRestaurant: UpdateRestaurant): Response<RestaurantListRequestItem>

    @DELETE("api/restaurant/delete/{id}")
    suspend fun deleteRestaurantById(@Path("id") id: String): Response<Void>

    @GET("/api/menuItems/restaurant/{id}")
    suspend fun menuRestaurantById(@Path("id") id: String): Response<List<MenuItemRestaurantResponse>>

    @POST("/api/menuItems/create")
    suspend fun postMenuRestaurant(@Body addMenuItemRestaurantRequest: AddMenuItemRestaurantRequest): Response<MenuItemRestaurantResponse>
}