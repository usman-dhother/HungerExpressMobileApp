package com.teamd.hungerexpressfooddelivery.ui.forgetpassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.teamd.hungerexpressfooddelivery.viewmodel.AuthViewModel
import com.teamd.hungerexpressfooddelivery.data.model.ForgetPasswordRequest
import com.teamd.hungerexpressfooddelivery.databinding.FragmentForgetPasswordBinding
import com.teamd.hungerexpressfooddelivery.ui.base.BaseFragment
import com.teamd.hungerexpressfooddelivery.utils.network.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgetPasswordFragment : BaseFragment() {

    private lateinit var forgetBinding: FragmentForgetPasswordBinding
    private val authViewMode by viewModels<AuthViewModel>()

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): ViewBinding {
        forgetBinding = FragmentForgetPasswordBinding.inflate(layoutInflater)
        return forgetBinding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindObserver()
        forgetBinding.forgetBtn.setOnClickListener {
            val email = forgetBinding.emailEt.text.toString()
            if (email.isEmpty()) {
                forgetBinding.emailTIL.error = "Please fill field to reset password"
                return@setOnClickListener
            }
            forgetBinding.emailTIL.error = null
            sendResetLink(email)
        }
    }

    private fun sendResetLink(email: String) {
        authViewMode.forgetPassword(ForgetPasswordRequest(email))
    }

    private fun bindObserver() {
        authViewMode.resetResponseLiveData.observe(viewLifecycleOwner, Observer {
            forgetBinding.progressBar.isVisible = false
            when (it) {
                is NetworkResult.Success -> {
                    Toast.makeText(requireContext(), it.data, Toast.LENGTH_LONG).show()
                }

                is NetworkResult.Error -> {

                    Toast.makeText(requireContext(), "Error!! ${it.message}", Toast.LENGTH_LONG)
                        .show()
                }

                is NetworkResult.Loading -> {
                    forgetBinding.progressBar.isVisible = true
                }
            }
        })
    }
}