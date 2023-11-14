package com.teamd.hungerexpressfooddelivery.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.teamd.hungerexpressfooddelivery.databinding.FragmentProfileBinding
import com.teamd.hungerexpressfooddelivery.ui.base.BaseFragment
import com.teamd.hungerexpressfooddelivery.ui.main.MainActivity
import com.teamd.hungerexpressfooddelivery.utils.AppPreferences
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileFragment : BaseFragment() {
    lateinit var profileBinding:FragmentProfileBinding
    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): ViewBinding {
        profileBinding = FragmentProfileBinding.inflate(layoutInflater)
        return profileBinding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val isAdmin = AppPreferences.userType != "user"
        if(isAdmin) {
            profileBinding.myOrderLayout.isVisible = false
            profileBinding.cartLayout.isVisible = false
        } else {
            profileBinding.myOrderLayout.isVisible = true
        }

        profileBinding.updateLayout.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToUpdateProfileFragment())
        }
        profileBinding.settingsLayout.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToSettingsFragment())
        }

        profileBinding.logoutLayout.setOnClickListener {
            AppPreferences.isUserLoggedIn = false
            AppPreferences.userName = ""
            AppPreferences.userId = ""
            MainActivity.start(requireActivity())
        }
    }
}