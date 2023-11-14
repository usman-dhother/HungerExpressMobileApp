package com.teamd.hungerexpressfooddelivery.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.teamd.hungerexpressfooddelivery.data.api.RestaurantApi
import com.teamd.hungerexpressfooddelivery.data.model.AddMenuItemRestaurantRequest
import com.teamd.hungerexpressfooddelivery.data.model.MenuItemRestaurantResponse
import com.teamd.hungerexpressfooddelivery.data.model.RestaurantListRequestItem
import com.teamd.hungerexpressfooddelivery.data.model.UpdateRestaurant
import com.teamd.hungerexpressfooddelivery.utils.constants.ConstNames.TAG
import com.teamd.hungerexpressfooddelivery.utils.network.NetworkResult
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class RestaurantRepository @Inject constructor(private val restaurantApi: RestaurantApi) {
    private val _restaurantResponseLiveData = MutableLiveData<NetworkResult<List<RestaurantListRequestItem>>>()
    val restaurantResponseLiveData: LiveData<NetworkResult<List<RestaurantListRequestItem>>>
        get() = _restaurantResponseLiveData

    private val _updateRestaurantResponseLiveData = MutableLiveData<NetworkResult<RestaurantListRequestItem>>()
    val updateRestaurantResponseLiveData: LiveData<NetworkResult<RestaurantListRequestItem>>
        get() = _updateRestaurantResponseLiveData

    private val _deleteRestaurantResponseLiveData = MutableLiveData<NetworkResult<String>>()
    val deleteRestaurantResponseLiveData: LiveData<NetworkResult<String>>
        get() = _deleteRestaurantResponseLiveData

    private val _menuRestaurantResponseLiveData = MutableLiveData<NetworkResult<List<MenuItemRestaurantResponse>>>()
    val menuRestaurantResponseLiveData: LiveData<NetworkResult<List<MenuItemRestaurantResponse>>>
        get() = _menuRestaurantResponseLiveData

    private val _postMenuRestaurantResponseLiveData = MutableLiveData<NetworkResult<MenuItemRestaurantResponse>>()
    val postMenuRestaurantResponseLiveData: LiveData<NetworkResult<MenuItemRestaurantResponse>>
        get() = _postMenuRestaurantResponseLiveData

    suspend fun restaurantList() {
        _restaurantResponseLiveData.postValue(NetworkResult.Loading())
        val response = restaurantApi.restaurantList()
        handleRestaurantResponse(response)
    }

    suspend fun updateRestaurantById(id:String,updateRestaurant: UpdateRestaurant) {
        _updateRestaurantResponseLiveData.postValue(NetworkResult.Loading())
        val response = restaurantApi.updateRestaurantById(id,updateRestaurant)
        handleUpdateRestaurantResponse(response)
    }

    suspend fun deleteRestaurantById(id:String) {
        _updateRestaurantResponseLiveData.postValue(NetworkResult.Loading())
        val response = restaurantApi.deleteRestaurantById(id)
        handleDeleteRestaurantResponse(response)
    }
    suspend fun menuRestaurantById(id:String) {
        _menuRestaurantResponseLiveData.postValue(NetworkResult.Loading())
        val response = restaurantApi.menuRestaurantById(id)
        handleMenuRestaurantResponse(response)
    }
    suspend fun postMenuRestaurant(addMenuItemRestaurantRequest: AddMenuItemRestaurantRequest) {
        _postMenuRestaurantResponseLiveData.postValue(NetworkResult.Loading())
        val response = restaurantApi.postMenuRestaurant(addMenuItemRestaurantRequest)
        handlePostMenuRestaurantResponse(response)
    }

    private fun handlePostMenuRestaurantResponse(response: Response<MenuItemRestaurantResponse>) {
        if (response.isSuccessful && response.body() != null) {
            _postMenuRestaurantResponseLiveData.postValue(NetworkResult.Success(response.body()))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _postMenuRestaurantResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("error")))
        } else {
            _postMenuRestaurantResponseLiveData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }

    private fun handleMenuRestaurantResponse(response: Response<List<MenuItemRestaurantResponse>>) {
        if (response.isSuccessful && response.body() != null) {
            _menuRestaurantResponseLiveData.postValue(NetworkResult.Success(response.body()))
        } else if (response.errorBody() != null) {
           val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _menuRestaurantResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("error")))
        } else {
            _menuRestaurantResponseLiveData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }

    private fun handleDeleteRestaurantResponse(response: Response<Void>) {
        if (response.isSuccessful) {
            Log.d(TAG, "handleDeleteRestaurantResponse: ${response.code()}")
            if(response.code() == 204) {
                _deleteRestaurantResponseLiveData.postValue(NetworkResult.Success("Successful Deleted"))
            }
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _deleteRestaurantResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("error")))
        } else {
            _deleteRestaurantResponseLiveData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }


    private fun handleUpdateRestaurantResponse(response: Response<RestaurantListRequestItem>) {
        if (response.isSuccessful && response.body() != null) {
            _updateRestaurantResponseLiveData.postValue(NetworkResult.Success(response.body()))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _updateRestaurantResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("error")))
        } else {
            _updateRestaurantResponseLiveData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }


    private fun handleRestaurantResponse(response: Response<List<RestaurantListRequestItem>>) {
        if (response.isSuccessful && response.body() != null) {
            _restaurantResponseLiveData.postValue(NetworkResult.Success(response.body()))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _restaurantResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("error")))
        } else {
            _restaurantResponseLiveData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }


}