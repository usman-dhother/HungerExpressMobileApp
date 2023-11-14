package com.teamd.hungerexpressfooddelivery.ui.restaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewbinding.ViewBinding
import com.teamd.hungerexpressfooddelivery.data.adapter.RestaurantMenuAdapter
import com.teamd.hungerexpressfooddelivery.data.model.RestaurantListRequestItem
import com.teamd.hungerexpressfooddelivery.databinding.FragmentRestaurantMenuBinding
import com.teamd.hungerexpressfooddelivery.interfaces.RestaurantItemClickedListener
import com.teamd.hungerexpressfooddelivery.ui.base.BaseFragment
import com.teamd.hungerexpressfooddelivery.utils.AppPreferences
import com.teamd.hungerexpressfooddelivery.utils.network.NetworkResult
import com.teamd.hungerexpressfooddelivery.viewmodel.RestaurantViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RestaurantMenuFragment : BaseFragment(), RestaurantItemClickedListener {
    private lateinit var menuBinding: FragmentRestaurantMenuBinding
    private val restaurantViewMode by viewModels<RestaurantViewModel>()
    private lateinit var restaurantMenuAdapter: RestaurantMenuAdapter
    private val args: RestaurantMenuFragmentArgs by navArgs()
    private lateinit var id: String

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): ViewBinding {
        menuBinding = FragmentRestaurantMenuBinding.inflate(layoutInflater)
        return menuBinding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        menuBinding.addMenuItemBtn.isVisible = AppPreferences.userType != "user"
        id = args.id
        restaurantMenuAdapter = RestaurantMenuAdapter(requireContext(), this)
        menuBinding.recyclerView.adapter = restaurantMenuAdapter
        bindObserver()
        restaurantViewMode.menuRestaurantById(id)

        menuBinding.addMenuItemBtn.setOnClickListener {
            findNavController().navigate(RestaurantMenuFragmentDirections.actionRestaurantMenuToAddRestaurantMenuItemFragment(id))
        }
    }

    private fun bindObserver() {
        restaurantViewMode.menuRestaurantResponseLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is NetworkResult.Success -> {
                    if (it.data != null) {
                        restaurantMenuAdapter.submitList(it.data)
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

    override fun onEditClicked(item: RestaurantListRequestItem) {

    }

    override fun onDeleteClicked(item: RestaurantListRequestItem) {

    }

    override fun onItemClicked(item: RestaurantListRequestItem) {

    }

}