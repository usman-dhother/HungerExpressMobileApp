package com.teamd.hungerexpressfooddelivery.ui.cart

import androidx.recyclerview.widget.DiffUtil

class CartItemDiffCallback : DiffUtil.ItemCallback<CartItem>() {
    override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
        return oldItem.menuItemId == newItem.menuItemId
    }

    override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
        return oldItem == newItem
    }
}

