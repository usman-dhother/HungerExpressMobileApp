package com.teamd.hungerexpressfooddelivery.ui.myorders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teamd.hungerexpressfooddelivery.databinding.OrderItemBinding

class OrderAdapter(private val orders: List<Order>) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding = OrderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orders[position]
        holder.bind(order)
    }

    override fun getItemCount(): Int = orders.size

    class OrderViewHolder(private val binding: OrderItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(order: Order) {
            binding.tvOrderId.text = "Order ID: ${order.orderId}"
            binding.tvItemName.text = "Item: ${order.itemName}"
            binding.tvQuantity.text = "Quantity: ${order.quantity}"
            binding.tvPrice.text = "Price: $${order.price}"
        }
    }
}
