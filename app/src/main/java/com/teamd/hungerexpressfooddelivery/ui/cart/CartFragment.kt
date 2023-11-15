
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teamd.hungerexpressfooddelivery.R
import com.teamd.hungerexpressfooddelivery.ui.cart.MenuItem
import com.teamd.hungerexpressfooddelivery.utils.AppPreferences

class CartFragment : Fragment(R.layout.fragment_cart) {

    private lateinit var cartAdapter: CartAdapter
    private lateinit var cartRecyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cartRecyclerView = view.findViewById(R.id.cartRecyclerView)
        cartRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Sample menu items
        val menuItems = listOf(
            MenuItem("Choco Brownie", "Choco brownies are all about chocolate...", 5.99, "In stock", "None"),
            MenuItem("Cupcakes", "Cupcakes are delightful, single-serving desserts...", 2.99, "In stock", "None"),
            MenuItem("Italian Pastries", "Italian pastries, known as 'pasticceria'...", 15.99, "In stock", "None")
        )

        cartAdapter = CartAdapter { cartItem ->
            // Handle interactions with cart items (e.g., remove item)
        }

        cartRecyclerView.adapter = cartAdapter

        updateCartUI()
    }

    private fun updateCartUI() {
        val cartItems = AppPreferences.cartItems
        cartAdapter.submitList(cartItems)
        // Update total price or any other UI elements as needed
    }
}
