package com.teamd.hungerexpressfooddelivery.ui.register


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
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
import com.teamd.hungerexpressfooddelivery.data.model.SignUpRequest
import com.teamd.hungerexpressfooddelivery.databinding.FragmentSignUpBinding
import com.teamd.hungerexpressfooddelivery.ui.base.BaseFragment
import com.teamd.hungerexpressfooddelivery.utils.network.NetworkResult
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SignUpFragment : BaseFragment() {

    private lateinit var signUpBinding: FragmentSignUpBinding
    private val authViewMode by viewModels<AuthViewModel>()

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): ViewBinding {
        signUpBinding = FragmentSignUpBinding.inflate(layoutInflater)
        return signUpBinding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindObserver()
        validatorListener()

        signUpBinding.signIpBtn.setOnClickListener {
            val action = SignUpFragmentDirections.actionSignUpFragmentToLoginFragment()
            findNavController().navigate(action)
        }

        signUpBinding.signUpBtn.setOnClickListener {
            val email = signUpBinding.emailEt.text.toString()
            val firstName = signUpBinding.firstNameEt.text.toString()
            val lastName = signUpBinding.lastNameEt.text.toString()
            val password = signUpBinding.passwordEt.text.toString()

            if (validateFirstName(firstName) && validateLastName(lastName) && validateEmail(email) && validatePassword(password)) {
                authViewMode.registerUser(
                    SignUpRequest(
                        email,
                        firstName,
                        lastName,
                        password
                    )
                )
            }
        }
    }

    private fun validateFirstName(s: String): Boolean {
        return if (s.isEmpty()) {
            signUpBinding.firstNameTIL.error = "Fill Field"
            false
        } else if (s.length < 3) {
            signUpBinding.firstNameTIL.error = "First name must be at least 3 characters"
            false
        } else {
            signUpBinding.firstNameTIL.error = null
            true
        }
    }

    private fun validateLastName(s: String): Boolean {
        return if (s.isEmpty()) {
            signUpBinding.lastNameTIL.error = "Fill Field"
            false
        } else if (s.length < 3) {
            signUpBinding.lastNameTIL.error = "Last name must be at least 3 characters"
            false
        } else {
            signUpBinding.lastNameTIL.error = null
            true
        }
    }

    private fun validatorListener() {
        signUpBinding.emailEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                validateEmail(s.toString())
            }

        })
        signUpBinding.passwordEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                validatePassword(s.toString())
            }

        })
        signUpBinding.firstNameEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                validateFirstName(s.toString())
            }

        })
        signUpBinding.lastNameEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                validateLastName(s.toString())
            }
        })
    }

    private fun validateEmail(text: String): Boolean {
        return if (text.isEmpty()) {
            signUpBinding.emailTIL.error = "Fill the field"
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(text).matches()) {
            signUpBinding.emailTIL.error = "Invalid Email"
            return false
        } else {
            signUpBinding.emailTIL.error = null
            return true
        }
    }

//    private val PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\\\d)(?=.*[$@$!%*#?&])[A-Za-z\\\\d$@$!%*#?&]{8,}$")

    private fun validatePassword(text: String): Boolean {
        return if (text.isEmpty()) {
            signUpBinding.passwordTIL.error = "Fill the field"
            false
        } else if (text.length < 9) {
            signUpBinding.passwordTIL.error = "Must be at least 9 Characters"
            return false
        } /*else if (!PASSWORD_PATTERN.matcher(text.trim()).matches()) {
            signUpBinding.passwordTIL.error = "Weak Password"
            return false
        } */ else {
            signUpBinding.passwordTIL.error = null
            return true
        }
    }

    private fun bindObserver() {
        authViewMode.userResponseLiveData.observe(viewLifecycleOwner, Observer {
            signUpBinding.progressBar.isVisible = false
            when (it) {
                is NetworkResult.Success -> {
                    Toast.makeText(requireContext(), "Register Successfully", Toast.LENGTH_LONG)
                        .show()
                    /*                    // token
                                        findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToLoginFragment())*/
                }

                is NetworkResult.Error -> {
//                    signUpBinding.errorMessageTv.text = it.message
                    Toast.makeText(requireContext(), "Error!! ${it.message}", Toast.LENGTH_LONG)
                        .show()
                }

                is NetworkResult.Loading -> {
                    signUpBinding.progressBar.isVisible = true
                }
            }
        })
    }
}