package com.teamd.hungerexpressfooddelivery.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamd.hungerexpressfooddelivery.data.model.AddMenuItemRestaurantRequest
import com.teamd.hungerexpressfooddelivery.data.model.MenuItemRestaurantResponse
import com.teamd.hungerexpressfooddelivery.data.model.RestaurantListRequestItem
import com.teamd.hungerexpressfooddelivery.data.model.UpdateRestaurant
import com.teamd.hungerexpressfooddelivery.repo.RestaurantRepository
import com.teamd.hungerexpressfooddelivery.utils.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(private val restaurantRepository: RestaurantRepository) :
    ViewModel() {

    val restaurantResponseLiveData: LiveData<NetworkResult<List<RestaurantListRequestItem>>>
        get() = restaurantRepository.restaurantResponseLiveData

    val updateRestaurantResponseLiveData: LiveData<NetworkResult<RestaurantListRequestItem>>
        get() = restaurantRepository.updateRestaurantResponseLiveData

    val deleteRestaurantResponseLiveData: LiveData<NetworkResult<String>>
        get() = restaurantRepository.deleteRestaurantResponseLiveData
    val menuRestaurantResponseLiveData: LiveData<NetworkResult<List<MenuItemRestaurantResponse>>>
        get() = restaurantRepository.menuRestaurantResponseLiveData

    val postMenuRestaurantResponseLiveData: LiveData<NetworkResult<MenuItemRestaurantResponse>>
        get() = restaurantRepository.postMenuRestaurantResponseLiveData


    fun restaurantList() {
        viewModelScope.launch {
            restaurantRepository.restaurantList()
        }
    }

    fun updateRestaurantById(id: String, updateRestaurant: UpdateRestaurant) {
        viewModelScope.launch {
            restaurantRepository.updateRestaurantById(id, updateRestaurant)
        }
    }
    fun deleteRestaurantById(id: String) {
        viewModelScope.launch {
            restaurantRepository.deleteRestaurantById(id)
        }
    }
    fun menuRestaurantById(id: String) {
        viewModelScope.launch {
            restaurantRepository.menuRestaurantById(id)
        }
    }
    fun postMenuRestaurant(addMenuItemRestaurantRequest: AddMenuItemRestaurantRequest) {
        viewModelScope.launch {
            restaurantRepository.postMenuRestaurant(addMenuItemRestaurantRequest)
        }
    }
}