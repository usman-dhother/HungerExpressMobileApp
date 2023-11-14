
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teamd.hungerexpressfooddelivery.R
import com.teamd.hungerexpressfooddelivery.databinding.FragmentCartBinding
import com.teamd.hungerexpressfooddelivery.utils.AppPreferences

// CartFragment.kt
class CartFragment : Fragment(R.layout.fragment_cart) {

    private lateinit var binding: FragmentCartBinding
    private lateinit var cartAdapter: CartAdapter
    private lateinit var cartRecyclerView: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapter: CartAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cartRecyclerView = view.findViewById(R.id.cartRecyclerView)
        layoutManager = LinearLayoutManager(requireContext())
        adapter = CartAdapter { /* Handle item interaction */ }

        cartRecyclerView.layoutManager = layoutManager
        cartRecyclerView.adapter = adapter


        binding = FragmentCartBinding.bind(view)

        // Initialize RecyclerView and set up the adapter
        cartAdapter = CartAdapter { cartItem ->
            // Handle interactions with cart items (e.g., remove item)
        }

        binding.cartRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cartAdapter
        }

        // Update UI with cart items
        updateCartUI()
    }

    private fun updateCartUI() {
        val cartItems = AppPreferences.cartItems
        cartAdapter.submitList(cartItems)
        // Update total price or any other UI elements as needed
    }
}
