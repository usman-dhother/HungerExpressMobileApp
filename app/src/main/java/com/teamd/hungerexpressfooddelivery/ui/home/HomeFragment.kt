package com.teamd.hungerexpressfooddelivery.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.teamd.hungerexpressfooddelivery.data.adapter.RestaurantAdapter
import com.teamd.hungerexpressfooddelivery.data.model.RestaurantListRequestItem
import com.teamd.hungerexpressfooddelivery.databinding.FragmentHomeBinding
import com.teamd.hungerexpressfooddelivery.interfaces.RestaurantItemClickedListener
import com.teamd.hungerexpressfooddelivery.ui.base.BaseFragment
import com.teamd.hungerexpressfooddelivery.ui.restaurant.EditRestaurantBottomSheetFragment
import com.teamd.hungerexpressfooddelivery.utils.network.NetworkResult
import com.teamd.hungerexpressfooddelivery.viewmodel.RestaurantViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment(), RestaurantItemClickedListener {
    private lateinit var homeBinding: FragmentHomeBinding
    private val restaurantViewMode by viewModels<RestaurantViewModel>()
    private lateinit var restaurantAdapter: RestaurantAdapter

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): ViewBinding {
        homeBinding = FragmentHomeBinding.inflate(layoutInflater)
        return homeBinding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        restaurantAdapter = RestaurantAdapter(requireContext(), this)
        homeBinding.rv.adapter = restaurantAdapter
        bindObserver()
        restaurantViewMode.restaurantList()
    }

    private fun bindObserver() {
        restaurantViewMode.restaurantResponseLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is NetworkResult.Success -> {
                    if (it.data != null) {
                        restaurantAdapter.submitList(it.data)
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
        restaurantViewMode.updateRestaurantResponseLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is NetworkResult.Success -> {
                    if (it.data != null) {
                        Toast.makeText(requireContext(), "Update Successfully", Toast.LENGTH_LONG)
                            .show()
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
        restaurantViewMode.deleteRestaurantResponseLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is NetworkResult.Success -> {
                    if (it.data != null) {
                        Toast.makeText(requireContext(), it.data, Toast.LENGTH_LONG).show()
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
        val editDialog = EditRestaurantBottomSheetFragment(item)
        editDialog.show(requireFragmentManager(), editDialog.tag)
    }

    override fun onDeleteClicked(item: RestaurantListRequestItem) {
        restaurantViewMode.deleteRestaurantById(item._id)
    }

    override fun onItemClicked(item: RestaurantListRequestItem) {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToRestaurantMenu(item._id))
    }
}