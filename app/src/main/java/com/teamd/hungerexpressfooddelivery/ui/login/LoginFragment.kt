package com.teamd.hungerexpressfooddelivery.ui.login


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.teamd.hungerexpressfooddelivery.viewmodel.AuthViewModel
import com.teamd.hungerexpressfooddelivery.data.model.SignInRequest
import com.teamd.hungerexpressfooddelivery.databinding.FragmentLoginBinding
import com.teamd.hungerexpressfooddelivery.ui.base.BaseFragment
import com.teamd.hungerexpressfooddelivery.ui.nav.NavActivity
import com.teamd.hungerexpressfooddelivery.utils.AppPreferences
import com.teamd.hungerexpressfooddelivery.utils.network.NetworkResult
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : BaseFragment() {

    private lateinit var loginBinding: FragmentLoginBinding
    private val authViewMode by viewModels<AuthViewModel>()

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): ViewBinding {
        loginBinding = FragmentLoginBinding.inflate(inflater)
        return loginBinding
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindObserver()

        loginBinding.signInBtn.setOnClickListener {
            val email = loginBinding.emailEt.text.toString().trim()
            val password = loginBinding.passwordEt.text.toString().trim()
            if (isNotValidCredential(email, password)) return@setOnClickListener
            authViewMode.loginUser(
                SignInRequest(
                    email,
                    password
                )
            )
        }

        loginBinding.signUpBtn.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToSignUpFragment()
            findNavController().navigate(action)
        }

        loginBinding.forgetTv.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToForgetPasswordFragment()
            findNavController().navigate(action)
        }
    }

    private fun isNotValidCredential(email: String, password: String): Boolean {
        if (email.isEmpty() && password.isEmpty()) {
            loginBinding.emailTIL.error = "Fill the Field"
            loginBinding.passwordTIL.error = "Fill the Field"
            return true
        }
        if (email.isEmpty()) {
            loginBinding.emailTIL.error = "Fill the Field"
            return true
        } else {
            loginBinding.emailTIL.error = null
        }
        if (password.isEmpty()) {
            loginBinding.passwordTIL.error = "Fill the Field"
            return true
        } else {
            loginBinding.passwordTIL.error = null
        }
        return false
    }


    private fun bindObserver() {
        authViewMode.userResponseLiveData.observe(viewLifecycleOwner, Observer {
            loginBinding.progressBar.isVisible = false
            when (it) {
                is NetworkResult.Success -> {
                    Toast.makeText(requireContext(), "Login Successfully", Toast.LENGTH_LONG).show()
                    if(it.data != null) {
                        AppPreferences.isUserLoggedIn = true
                        AppPreferences.userId = it.data._id
                        AppPreferences.userName = it.data.username
                        AppPreferences.userType = it.data.user_type
                    }
                    NavActivity.start(requireActivity())
                }

                is NetworkResult.Error -> {

                    Toast.makeText(requireContext(), "Error!! ${it.message}", Toast.LENGTH_LONG)
                        .show()
                }

                is NetworkResult.Loading -> {
                    loginBinding.progressBar.isVisible = true
                }
            }
        })
    }


}