package com.teamd.hungerexpressfooddelivery.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.teamd.hungerexpressfooddelivery.R
import com.teamd.hungerexpressfooddelivery.data.model.MenuItemRestaurantResponse
import com.teamd.hungerexpressfooddelivery.databinding.ItemRestaurantMenuBinding
import com.teamd.hungerexpressfooddelivery.interfaces.RestaurantItemClickedListener
import com.teamd.hungerexpressfooddelivery.ui.cart.CartItem
import com.teamd.hungerexpressfooddelivery.utils.AppPreferences

class RestaurantMenuAdapter (
    private val context: Context,
    private val listener: RestaurantItemClickedListener
):  ListAdapter<MenuItemRestaurantResponse, RestaurantMenuAdapter.RestaurantMenuViewHolder>(RestaurantMenuAdapter.DiffUtil()) {

    var count:Int = -1
    class DiffUtil: androidx.recyclerview.widget.DiffUtil.ItemCallback<MenuItemRestaurantResponse>() {


        override fun areItemsTheSame(
            oldItem: MenuItemRestaurantResponse,
            newItem: MenuItemRestaurantResponse
        ): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(
            oldItem: MenuItemRestaurantResponse,
            newItem: MenuItemRestaurantResponse
        ): Boolean {
            return oldItem == newItem
        }

//        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//            val menuItem = getItem(position)
//
//            // Existing code...
//
//            holder.itemView.setOnClickListener {
//                // Add the selected item to the cart
//                val cartItem = CartItem(
//                    menuItem.id, // Assuming menuItem has an 'id' property
//                    menuItem.name,
//                    menuItem.price,
//                    1 // Initial quantity
//                )
//                AppPreferences.cartItems = AppPreferences.cartItems + cartItem
//                notifyDataSetChanged()
//            }
//        }

    }

    inner class RestaurantMenuViewHolder(private val binding: ItemRestaurantMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(restaurant: MenuItemRestaurantResponse) {
            // Bind data to the view elements using ViewBinding
            if(AppPreferences.userType == "user") {
                binding.editBtn.isVisible = false
                binding.deleteBtn.isVisible = false
            }

            Glide.with(context)
                .load(restaurant.image_url)
                .placeholder(R.drawable.loader)
                .into(binding.restaurantIv)

            binding.titleTv.text = restaurant.name
            binding.priceTv.setText("Price: $${restaurant.price}")
            binding.availabilityTv.setText("Availability: ${restaurant.availability}")
            binding.allergyTv.setText("Allergy Info: ${restaurant.allergy_info}")

            binding.addBtn.setOnClickListener {
                if(count == 100) {
                    return@setOnClickListener
                }
                count += 1
                binding.itemCountTv.text = count.toString()
            }
            binding.minusBtn.setOnClickListener {
                if(count == 0) {
                    return@setOnClickListener
                }
                count -= 1
                binding.itemCountTv.text = count.toString()
            }

            binding.addToCartBtn.setOnClickListener {

            }
            binding.editBtn.setOnClickListener {
            }

            binding.deleteBtn.setOnClickListener {
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantMenuViewHolder {
        val binding =
            ItemRestaurantMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RestaurantMenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantMenuViewHolder, position: Int) {
        val currentRestaurant = getItem(position)
        holder.bind(currentRestaurant)

        val menuItem = getItem(position)

        // Existing code...

        holder.itemView.setOnClickListener {
            // Add the selected item to the cart
            val cartItem = CartItem(
                menuItem._id, // Assuming menuItem has an 'id' property
                menuItem.name,
                menuItem.price,
                1 // Initial quantity
            )
            AppPreferences.cartItems = AppPreferences.cartItems + cartItem
            notifyDataSetChanged()
        }
    }


}