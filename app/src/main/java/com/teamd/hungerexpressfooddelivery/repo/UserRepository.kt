package com.teamd.hungerexpressfooddelivery.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.teamd.hungerexpressfooddelivery.data.api.UserApi
import com.teamd.hungerexpressfooddelivery.data.model.ForgetPasswordRequest
import com.teamd.hungerexpressfooddelivery.data.model.ForgetPasswordResponse
import com.teamd.hungerexpressfooddelivery.data.model.RestaurantListRequestItem
import com.teamd.hungerexpressfooddelivery.data.model.SignInRequest
import com.teamd.hungerexpressfooddelivery.data.model.SignUpRequest
import com.teamd.hungerexpressfooddelivery.data.model.UserResponse
import com.teamd.hungerexpressfooddelivery.utils.constants.ConstNames.TAG
import com.teamd.hungerexpressfooddelivery.utils.network.NetworkResult
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(private val userApi:UserApi) {

    private val _userResponseLiveData = MutableLiveData<NetworkResult<UserResponse>>()
    val userResponseLiveData: LiveData<NetworkResult<UserResponse>>
        get() = _userResponseLiveData

    private val _userInfoResponseLiveData = MutableLiveData<NetworkResult<UserResponse>>()
    val userInfoResponseLiveData: LiveData<NetworkResult<UserResponse>>
        get() = _userInfoResponseLiveData

    private val _resetPasswordResponseLiveData = MutableLiveData<NetworkResult<String>>()
    val resetPasswordResponseLiveData: LiveData<NetworkResult<String>>
        get() = _resetPasswordResponseLiveData

    suspend fun registerUser(signUpRequest: SignUpRequest){
        _userResponseLiveData.postValue(NetworkResult.Loading())
        val response = userApi.signup(signUpRequest)
        handleResponse(response)
    }

    suspend fun getUserById(id: String){
        _userInfoResponseLiveData.postValue(NetworkResult.Loading())
        val response = userApi.getUserById(id)
        handleUserInfoResponse(response)
    }

    private fun handleUserInfoResponse(response: Response<UserResponse>) {
        if (response.isSuccessful && response.body() != null) {
            _userInfoResponseLiveData.postValue(NetworkResult.Success(response.body()))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _userInfoResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("error")))
        } else {
            _userInfoResponseLiveData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }

    suspend fun loginUser(signInRequest: SignInRequest){
        _userResponseLiveData.postValue(NetworkResult.Loading())
        val response = userApi.signin(signInRequest)
        handleResponse(response)
    }

    private fun handleResponse(response: Response<UserResponse>) {
        if (response.isSuccessful && response.body() != null) {
            _userResponseLiveData.postValue(NetworkResult.Success(response.body()))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _userResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("error")))
        } else {
            _userResponseLiveData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }

    suspend fun forgetPassword(request: ForgetPasswordRequest) {
        _resetPasswordResponseLiveData.postValue(NetworkResult.Loading())
        val response = userApi.forgetPassword(request)
        handleForgetResponse(response)
    }

    private fun handleForgetResponse(response: ResponseBody) {
        try {
            val res = response.charStream().readText()
            Log.d(TAG, "handleForgetResponse: $res")
            if (res == "Email Sent Successfully!!") {
//                val forgetResponse = ForgetPasswordResponse(response)
                _resetPasswordResponseLiveData.postValue(NetworkResult.Success(res))
            } else {
                // Handle other cases
                _resetPasswordResponseLiveData.postValue(NetworkResult.Error(res))
            }
        } catch (e: Exception) {
            // Handle exceptions (e.g., network errors)
            _resetPasswordResponseLiveData.postValue(NetworkResult.Error(e.message))
        }
    }

}