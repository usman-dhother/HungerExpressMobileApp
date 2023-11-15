package com.teamd.hungerexpressfooddelivery.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teamd.hungerexpressfooddelivery.databinding.CartItemBinding
import com.teamd.hungerexpressfooddelivery.ui.cart.MenuItem

class CartItemAdapter(private val menuItems: List<MenuItem>) : RecyclerView.Adapter<CartItemAdapter.CartItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        val binding = CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        val menuItem = menuItems[position]
        holder.bind(menuItem)
    }

    override fun getItemCount(): Int = menuItems.size

    class CartItemViewHolder(private val binding: CartItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(menuItem: MenuItem) {
            binding.tvItemName.text = menuItem.name
            binding.tvDescription.text = menuItem.description
            binding.tvPrice.text = "Price: $${menuItem.price}"
            // Set other fields like availability, allergy info, etc.
        }
    }
}
