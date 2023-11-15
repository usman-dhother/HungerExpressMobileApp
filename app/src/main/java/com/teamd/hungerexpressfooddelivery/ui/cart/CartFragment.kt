package com.teamd.hungerexpressfooddelivery.ui.cart

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teamd.hungerexpressfooddelivery.R
import com.teamd.hungerexpressfooddelivery.utils.AppPreferences

class CartFragment : Fragment(R.layout.fragment_cart) {

    private lateinit var cartAdapter: CartAdapter
    private lateinit var cartRecyclerView: RecyclerView
    private val menuItems = listOf(
        MenuItem("Lobster Bisque", "Creamy lobster bisque with a hint of sherry.", 10.99, "In stock", "lobster-bisque.jpg", 1),
        MenuItem("Seafood Platter", "A platter of assorted seafood including shrimp, crab legs, and mussels…", 24.99, "In stock", "seafood-platter.jpg", 1),
        // ... Add other items similarly, including the quantity parameter
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cartRecyclerView = view.findViewById(R.id.cartRecyclerView)
        cartRecyclerView.layoutManager = LinearLayoutManager(requireContext())


        cartAdapter = CartAdapter { cartItem, isRemoveAction ->
            if (isRemoveAction) {
                removeItemFromCart(cartItem)
            }
            // Add other interactions if needed
        }

        cartRecyclerView.adapter = cartAdapter

        updateCartUI()
    }

    private fun removeItemFromCart(cartItem: CartItem) {
        // Logic to remove item from cart
        AppPreferences.cartItems = AppPreferences.cartItems.filter { it != cartItem }
        updateCartUI()
    }

    private fun updateCartUI() {
        // Convert menu items to cart items
        val cartItems = menuItems.map { menuItem ->
            CartItem(menuItem.name, menuItem.description, menuItem.price, 1) // Assuming quantity as 1 for stubbing
        }
        cartAdapter.submitList(cartItems)
        // Update total price or any other UI elements as needed
    }
}
