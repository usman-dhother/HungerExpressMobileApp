package com.teamd.hungerexpressfooddelivery.ui.restaurant

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.viewbinding.ViewBinding
import com.teamd.hungerexpressfooddelivery.data.model.AddMenuItemRestaurantRequest
import com.teamd.hungerexpressfooddelivery.databinding.FragmentAddMenuItemBinding
import com.teamd.hungerexpressfooddelivery.ui.base.BaseFragment
import com.teamd.hungerexpressfooddelivery.utils.network.NetworkResult
import com.teamd.hungerexpressfooddelivery.viewmodel.RestaurantViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddRestaurantMenuItemFragment : BaseFragment() {
    private lateinit var addMenuBinding: FragmentAddMenuItemBinding
    private val args: AddRestaurantMenuItemFragmentArgs by navArgs()
    private lateinit var id: String
    private lateinit var name: String
    private lateinit var desc: String
    private lateinit var price: String
    private lateinit var availability: String
    private lateinit var imgUrl: String

    private val restaurantViewMode by viewModels<RestaurantViewModel>()


    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): ViewBinding {
        addMenuBinding = FragmentAddMenuItemBinding.inflate(layoutInflater)
        return addMenuBinding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        listener()
        bindObserver()
    }

    private fun bindObserver() {


        restaurantViewMode.postMenuRestaurantResponseLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is NetworkResult.Success -> {
                    if (it.data != null) {
                        Toast.makeText(requireContext(), "Menu Add Successful", Toast.LENGTH_LONG)
                            .show()
                    }
                }

                is NetworkResult.Error -> {
                    Toast.makeText(requireContext(), "Error!! ${it.message}", Toast.LENGTH_LONG)
                        .show()
                }

                is NetworkResult.Loading -> {
                    Toast.makeText(requireContext(), "Menu Adding", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun listener() {
        addMenuBinding.availabilityACTV.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                if (position == 0) {
                    // "Select Availability" is selected
                    addMenuBinding.availabilityTIL.error = "Please select an option"
                } else {
                    availability = items[position]
                    addMenuBinding.availabilityTIL.error = null // Clear any previous error
                }
            }

        addMenuBinding.availabilityACTV.setOnItemClickListener { parent, view, position, id ->
            if (position > 0) {
                // User selected an item other than the hint
                availability = items[position]
                addMenuBinding.availabilityTIL.error = null // Clear any previous error
            }
        }
        addMenuBinding.chooseFileBtn.setOnClickListener {
            pickImage()
        }

        addMenuBinding.saveBtn.setOnClickListener {
            name = addMenuBinding.nameEt.text.toString()
            desc = addMenuBinding.descEt.text.toString()
            price = addMenuBinding.priceEt.text.toString()

            if (name.isEmpty()) {
                addMenuBinding.nameTIL.error = "Please Fill the Field"
                return@setOnClickListener
            } else {
                addMenuBinding.nameTIL.error = null
            }
            if (desc.isEmpty()) {
                addMenuBinding.descTIL.error = "Please Fill the Field"
                return@setOnClickListener
            } else {
                addMenuBinding.descTIL.error = null
            }
            if (price.isEmpty()) {
                addMenuBinding.priceTIL.error = "Please Fill the Field"
                return@setOnClickListener
            } else {
                addMenuBinding.priceTIL.error = null
            }

            if (availability.isEmpty()) {
                addMenuBinding.availabilityTIL.error = "Please Select Option"
                return@setOnClickListener
            } else {
                addMenuBinding.availabilityTIL.error = null
            }

            if (imgUrl.isEmpty()) {
                imgUrl = addMenuBinding.imageUrlEt.text.toString()
                if (imgUrl.isEmpty()) {
                    addMenuBinding.imageUrlTIL.error = "Please enter image url or choose file"
                    addMenuBinding.chooseFileTv.text = "Please Choose Image"
                    return@setOnClickListener
                } else {
                    addMenuBinding.imageUrlTIL.error = null
                }
            }

            postRestaurantMenuItem()
        }
    }

    private fun postRestaurantMenuItem() {
        val priceDB = price.toFloat()
        restaurantViewMode.postMenuRestaurant(
            AddMenuItemRestaurantRequest(
                availability,
                desc,
                imgUrl,
                name,
                priceDB,
                id
            )
        )
    }

    private fun pickImage() {
        val intent = Intent(MediaStore.ACTION_PICK_IMAGES)
        startActivityForResult(intent, 101)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == 101) {
                val uri = data?.data
                addMenuBinding.imageUrlEt.setText(uri.toString())
                imgUrl = uri.toString()
                addMenuBinding.chooseFileTv.text = "Image is Selected"
            }
        }
    }

    private lateinit var items: Array<String>
    private fun initView() {
        id = args.id
        addMenuBinding.restaurantID.setText(id)
        addMenuBinding.restaurantID.isEnabled = false
        // Create an ArrayAdapter with your items
        items = arrayOf("Select Availability", "In stock", "Out stock")
        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, items)
        addMenuBinding.availabilityACTV.setAdapter(adapter)
    }

}