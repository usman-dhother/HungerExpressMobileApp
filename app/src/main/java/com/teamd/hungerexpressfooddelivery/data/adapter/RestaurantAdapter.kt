package com.teamd.hungerexpressfooddelivery.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.teamd.hungerexpressfooddelivery.R
import com.teamd.hungerexpressfooddelivery.data.model.RestaurantListRequestItem
import com.teamd.hungerexpressfooddelivery.databinding.ItemRestaurantsBinding
import com.teamd.hungerexpressfooddelivery.interfaces.RestaurantItemClickedListener
import com.teamd.hungerexpressfooddelivery.utils.AppPreferences

class RestaurantAdapter(
    private val context: Context,
    private val listener: RestaurantItemClickedListener
) : ListAdapter<RestaurantListRequestItem, RestaurantAdapter.RestaurantViewHolder>(DiffUtil()) {

    class DiffUtil: androidx.recyclerview.widget.DiffUtil.ItemCallback<RestaurantListRequestItem>() {
        override fun areItemsTheSame(
            oldItem: RestaurantListRequestItem,
            newItem: RestaurantListRequestItem
        ): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(
            oldItem: RestaurantListRequestItem,
            newItem: RestaurantListRequestItem
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val binding =
            ItemRestaurantsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RestaurantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val currentRestaurant = getItem(position)
        holder.bind(currentRestaurant)
    }

    inner class RestaurantViewHolder(private val binding: ItemRestaurantsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(restaurant: RestaurantListRequestItem) {
            // Bind data to the view elements using ViewBinding
             if(AppPreferences.userType == "user") {
                 binding.editBtn.isVisible = false
                 binding.deleteBtn.isVisible = false
             }

            Glide.with(context)
                .load(restaurant.restaurantImg)
                .placeholder(R.drawable.loader)
                .into(binding.restaurantIv)

            binding.titleTv.text = restaurant.name
            binding.descriptionTv.text = restaurant.description
            binding.addressTv.text = restaurant.address
            binding.phoneNumberTv.text = restaurant.phone_number

            binding.editBtn.setOnClickListener {
                listener.onEditClicked(restaurant)
            }

            binding.deleteBtn.setOnClickListener {
                listener.onDeleteClicked(restaurant)
            }

            itemView.setOnClickListener {
                listener.onItemClicked(restaurant)
            }
        }
    }
}