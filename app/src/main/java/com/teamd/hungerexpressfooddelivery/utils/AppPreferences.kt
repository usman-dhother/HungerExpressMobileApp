package com.teamd.hungerexpressfooddelivery.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.teamd.hungerexpressfooddelivery.ui.cart.CartItem

object AppPreferences {
    private const val NAME = "HungerExpressPreferences"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    // Initialize the SharedPreferences instance.
    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    // User login status
    var isUserLoggedIn: Boolean
        get() = preferences.getBoolean("isUserLoggedIn", false)
        set(value) = preferences.edit().putBoolean("isUserLoggedIn", value).apply()

    // User ID
    var userId: String
        get() = preferences.getString("userId", "") ?: ""
        set(value) = preferences.edit().putString("userId", value).apply()

    // User Name
    var userName: String
        get() = preferences.getString("userName", "") ?: ""
        set(value) = preferences.edit().putString("userName", value).apply()
    // User Type
    var userType: String
        get() = preferences.getString("userType", "") ?: ""
        set(value) = preferences.edit().putString("userType", value).apply()


    // Cart items
    var cartItems: List<CartItem>
        get() {
            val json = preferences.getString("cartItems", null)
            return json?.let {
                Gson().fromJson(it, object : TypeToken<List<CartItem>>() {}.type)
            } ?: emptyList()
        }
        set(value) {
            val json = Gson().toJson(value)
            preferences.edit().putString("cartItems", json).apply()
        }
}