package com.teamd.hungerexpressfooddelivery.ui.restaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.teamd.hungerexpressfooddelivery.databinding.FragmentRestaurantBinding
import com.teamd.hungerexpressfooddelivery.ui.base.BaseFragment


class RestaurantFragment : BaseFragment() {
    lateinit var binding:FragmentRestaurantBinding
    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): ViewBinding {
        binding = FragmentRestaurantBinding.inflate(layoutInflater)
        return binding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addLayout.setOnClickListener {
            showSheetOfAddRestaurantLayout()
        }

        binding.deleteRestaurantLayout.setOnClickListener {
            showSheetOfDeleteRestaurantLayout()
        }
        binding.modifyLayout.setOnClickListener {
            showSheetOfModifyRestaurantLayout()
        }
    }

    private fun showSheetOfModifyRestaurantLayout() {

    }

    private fun showSheetOfDeleteRestaurantLayout() {
    }

    private fun showSheetOfAddRestaurantLayout() {
    }
}