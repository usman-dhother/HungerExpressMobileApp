package com.teamd.hungerexpressfooddelivery.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamd.hungerexpressfooddelivery.data.model.ForgetPasswordRequest
import com.teamd.hungerexpressfooddelivery.data.model.RestaurantListRequestItem
import com.teamd.hungerexpressfooddelivery.data.model.SignInRequest
import com.teamd.hungerexpressfooddelivery.data.model.SignUpRequest
import com.teamd.hungerexpressfooddelivery.data.model.UserResponse
import com.teamd.hungerexpressfooddelivery.repo.UserRepository
import com.teamd.hungerexpressfooddelivery.utils.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel() {

    val userResponseLiveData: LiveData<NetworkResult<UserResponse>>
        get() = userRepository.userResponseLiveData

    val userInfoResponseLiveData: LiveData<NetworkResult<UserResponse>>
        get() = userRepository.userInfoResponseLiveData

    val resetResponseLiveData: LiveData<NetworkResult<String>>
        get() = userRepository.resetPasswordResponseLiveData

    fun registerUser(signUpRequest: SignUpRequest) {
        viewModelScope.launch {
            userRepository.registerUser(signUpRequest)
        }
    }

    fun loginUser(signInRequest: SignInRequest) {
        viewModelScope.launch {
            userRepository.loginUser(signInRequest)
        }
    }

    fun forgetPassword(forgetPasswordRequest: ForgetPasswordRequest) {
        viewModelScope.launch {
            userRepository.forgetPassword(forgetPasswordRequest)
        }
    }

    fun getUserById(id:String) {
        viewModelScope.launch {
            userRepository.getUserById(id)
        }
    }


}