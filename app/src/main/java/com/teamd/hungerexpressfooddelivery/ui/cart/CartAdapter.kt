
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teamd.hungerexpressfooddelivery.R
import com.teamd.hungerexpressfooddelivery.ui.cart.CartItem
import com.teamd.hungerexpressfooddelivery.ui.cart.CartItemDiffCallback

class CartAdapter(private val onCartItemInteraction: (CartItem) -> Unit) :
    ListAdapter<CartItem, CartAdapter.ViewHolder>(CartItemDiffCallback()) {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemName: TextView = itemView.findViewById(R.id.itemNameTextView)
        private val quantity: TextView = itemView.findViewById(R.id.quantityTextView)
        private val price: TextView = itemView.findViewById(R.id.priceTextView)

        fun bind(cartItem: CartItem) {
            itemName.text = cartItem.name
            quantity.text = cartItem.quantity.toString()
            price.text = cartItem.price.toString()

            itemView.setOnClickListener {
                onCartItemInteraction(cartItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
