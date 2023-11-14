package com.teamd.hungerexpressfooddelivery.ui.updateprofile

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.teamd.hungerexpressfooddelivery.viewmodel.AuthViewModel
import com.teamd.hungerexpressfooddelivery.databinding.FragmentUpdateProfileBinding
import com.teamd.hungerexpressfooddelivery.ui.base.BaseFragment
import com.teamd.hungerexpressfooddelivery.utils.AppPreferences
import com.teamd.hungerexpressfooddelivery.utils.constants.ConstNames.TAG
import com.teamd.hungerexpressfooddelivery.utils.network.NetworkResult
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UpdateProfileFragment : BaseFragment() {

    private lateinit var updateBinding: FragmentUpdateProfileBinding
    private val authViewMode by viewModels<AuthViewModel>()
    private lateinit var firstName:String
    private lateinit var lastName:String
    private lateinit var email:String

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): ViewBinding {
        updateBinding  = FragmentUpdateProfileBinding.inflate(layoutInflater)
        return updateBinding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindObserverForGetUseData()
        authViewMode.getUserById(AppPreferences.userId)

        updateBinding.changePwdBtn.setOnClickListener {
            Toast.makeText(requireContext(),"Under Progress",Toast.LENGTH_LONG).show()
        }

        validatorListener()
        updateBinding.updateBtn.setOnClickListener {
            email = updateBinding.emailEt.text.toString()
            firstName = updateBinding.firstNameEt.text.toString()
            lastName = updateBinding.lastNameEt.text.toString()

            /*if (validateFirstName(firstName) && validateLastName(lastName) && validateEmail(email) && validatePassword(password)) {
                authViewMode.registerUser(
                    SignUpRequest(
                        email,
                        firstName,
                        lastName
                    )
                )
            }*/
        }

      /*
        bindObserver()



        updateBinding.updateBtn.setOnClickListener {
            val email = updateBinding.emailEt.text.toString()
            val firstName = updateBinding.firstNameEt.text.toString()
            val lastName = updateBinding.lastNameEt.text.toString()
            val password = updateBinding.passwordEt.text.toString()

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
        */

    }


    private fun bindObserverForGetUseData() {
        authViewMode.userInfoResponseLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is NetworkResult.Success -> {
                    val user = it.data
                    Log.d(TAG, "bindObserverForGetUseData: $user")
                    if(user != null) {
                        firstName = user.first_name
                        lastName = user.last_name
                        email = user.email
                        updateUI()
                    }
                }

                is NetworkResult.Error -> {
                    Toast.makeText(requireContext(), "Error!! ${it.message}", Toast.LENGTH_LONG)
                        .show()
                }

                is NetworkResult.Loading -> {

                }
            }
        })
    }

    private fun updateUI() {
        updateBinding.firstNameEt.setText(firstName)
        updateBinding.lastNameEt.setText(lastName)
        updateBinding.emailEt.setText(email)
    }

    private fun validateFirstName(s: String): Boolean {
        return if (s.isEmpty()) {
            updateBinding.firstNameTIL.error = "Fill Field"
            false
        } else if (s.length < 3) {
            updateBinding.firstNameTIL.error = "First name must be at least 3 characters"
            false
        } else {
            updateBinding.firstNameTIL.error = null
            true
        }
    }

    private fun validateLastName(s: String): Boolean {
        return if (s.isEmpty()) {
            updateBinding.lastNameTIL.error = "Fill Field"
            false
        } else if (s.length < 3) {
            updateBinding.lastNameTIL.error = "Last name must be at least 3 characters"
            false
        } else {
            updateBinding.lastNameTIL.error = null
            true
        }
    }

    private fun validatorListener() {
        updateBinding.emailEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                validateEmail(s.toString())
            }

        })
        updateBinding.passwordEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                validatePassword(s.toString())
            }

        })
        updateBinding.firstNameEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                validateFirstName(s.toString())
            }

        })
        updateBinding.lastNameEt.addTextChangedListener(object : TextWatcher {
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
            updateBinding.emailTIL.error = "Fill the field"
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(text).matches()) {
            updateBinding.emailTIL.error = "Invalid Email"
            return false
        } else {
            updateBinding.emailTIL.error = null
            return true
        }
    }

    private fun validatePassword(text: String): Boolean {
        return if (text.isEmpty()) {
            updateBinding.passwordTIL.error = "Fill the field"
            false
        } else if (text.length < 9) {
            updateBinding.passwordTIL.error = "Must be at least 9 Characters"
            return false
        } /*else if (!PASSWORD_PATTERN.matcher(text.trim()).matches()) {
            signUpBinding.passwordTIL.error = "Weak Password"
            return false
        } */ else {
            updateBinding.passwordTIL.error = null
            return true
        }
    }

    private fun bindObserver() {
        authViewMode.userResponseLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is NetworkResult.Success -> {
                    Toast.makeText(requireContext(), "Update Successfully", Toast.LENGTH_LONG)
                        .show()
                }

                is NetworkResult.Error -> {
//                    signUpBinding.errorMessageTv.text = it.message
                    Toast.makeText(requireContext(), "Error!! ${it.message}", Toast.LENGTH_LONG)
                        .show()
                }

                is NetworkResult.Loading -> {
                    Toast.makeText(requireContext(), "Update In Progress", Toast.LENGTH_LONG)
                        .show()
                }
            }
        })
    }
}