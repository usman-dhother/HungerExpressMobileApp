package com.teamd.hungerexpressfooddelivery.ui.myorders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.teamd.hungerexpressfooddelivery.R
class OrderAdapter(private val orders: List<Order>) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.order_item, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(orders[position])
    }

    override fun getItemCount(): Int = orders.size

    class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvOrderStatus: TextView = itemView.findViewById(R.id.tvOrderStatus)
        private val tvDeliveryAddress: TextView = itemView.findViewById(R.id.tvDeliveryAddress)
        private val tvTotalPrice: TextView = itemView.findViewById(R.id.tvTotalPrice)
        private val orderItemsContainer: LinearLayout = itemView.findViewById(R.id.orderItemsContainer)

        init {
            itemView.setOnClickListener {
                orderItemsContainer.visibility = if (orderItemsContainer.visibility == View.VISIBLE) View.GONE else View.VISIBLE
            }
        }

        fun bind(order: Order) {
            tvOrderStatus.text = order.order_status
            tvDeliveryAddress.text = order.delivery_address
            tvTotalPrice.text = "Total Price: $${order.total_price}"

            orderItemsContainer.visibility = View.GONE
            orderItemsContainer.removeAllViews()

            // Add views for each order item
            order.order_items.forEach { item ->
                val itemLayout = LayoutInflater.from(orderItemsContainer.context).inflate(R.layout.item_order_item, orderItemsContainer, false)
                val itemName: TextView = itemLayout.findViewById(R.id.itemName) // Ensure this ID exists in your item_order_item.xml
                val itemDescription: TextView = itemLayout.findViewById(R.id.itemDescription) // Ensure this ID exists
                val itemPrice: TextView = itemLayout.findViewById(R.id.itemPrice) // Ensure this ID exists

                itemName.text = item.name
                itemDescription.text = item.description
                itemPrice.text = "Price: $${item.price}"

                orderItemsContainer.addView(itemLayout)
            }
        }
    }
}