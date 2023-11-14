package com.teamd.hungerexpressfooddelivery.ui.restaurant

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.teamd.hungerexpressfooddelivery.data.model.RestaurantListRequestItem
import com.teamd.hungerexpressfooddelivery.data.model.UpdateRestaurant
import com.teamd.hungerexpressfooddelivery.databinding.FragmentEditRestaurantBottomSheetBinding
import com.teamd.hungerexpressfooddelivery.viewmodel.RestaurantViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EditRestaurantBottomSheetFragment(private val restaurant: RestaurantListRequestItem) : BottomSheetDialogFragment() {

    private lateinit var binding:FragmentEditRestaurantBottomSheetBinding
    private val restaurantViewMode by viewModels<RestaurantViewModel>()
    // Create a flag to track changes
    private var isRestaurantDataChanged = false
    private lateinit var name:String
    private lateinit var desc:String
    private lateinit var address:String
    private lateinit var number:String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditRestaurantBottomSheetBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.restaurantAddressEt.setText(restaurant.address)
        binding.restaurantNameEt.setText(restaurant.name)
        binding.restaurantNumberEt.setText(restaurant.phone_number)
        binding.restaurantDescEt.setText(restaurant.description)

        // You can also enable the save button here, if it was initially disabled
        binding.saveBtn.isEnabled = false

        binding.restaurantNameEt.addTextChangedListener(textWatcher)
        binding.restaurantDescEt.addTextChangedListener(textWatcher)
        binding.restaurantAddressEt.addTextChangedListener(textWatcher)
        binding.restaurantNumberEt.addTextChangedListener(textWatcher)

        binding.saveBtn.setOnClickListener {
            saveRestaurantData()
            dismiss()
        }

        binding.clostIv.setOnClickListener {
            if (isRestaurantDataChanged) {
                // Show a confirmation dialog
                val alertDialog = AlertDialog.Builder(requireContext())
                alertDialog.setTitle("Save Changes")
                alertDialog.setMessage("Do you want to save the changes you made?")
                alertDialog.setPositiveButton("Save") { _, _ ->
                    // Handle save action here
                    saveRestaurantData()
                    dismiss()
                }
                alertDialog.setNegativeButton("Cancel") { _, _ ->
                    // User chose to cancel, so keep the dialog open
                }
                alertDialog.setNeutralButton("Discard") { _, _ ->
                    // User chose to discard changes, so simply dismiss the dialog
                    dismiss()
                }
                alertDialog.show()
            } else {
                // Data has not changed, so simply dismiss the dialog
                dismiss()
            }
        }
    }

    private fun saveRestaurantData() {
        name = binding.restaurantNameEt.text.toString()
        desc = binding.restaurantDescEt.text.toString()
        address = binding.restaurantAddressEt.text.toString()
        number = binding.restaurantNumberEt.text.toString()
        val id = restaurant._id
        restaurantViewMode.updateRestaurantById(id,UpdateRestaurant(address,desc,name,number))
    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // Not used, but required by the TextWatcher interface
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            // This method is called when the text in the EditText is changed
            isRestaurantDataChanged = true

            // You can also enable the save button here, if it was initially disabled
            binding.saveBtn.isEnabled = true
        }

        override fun afterTextChanged(s: Editable?) {
            // Not used, but required by the TextWatcher interface
        }
    }

}