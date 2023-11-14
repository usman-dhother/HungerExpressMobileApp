package com.teamd.hungerexpressfooddelivery.ui.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.teamd.hungerexpressfooddelivery.databinding.ActivityMainBinding
import com.teamd.hungerexpressfooddelivery.ui.nav.NavActivity
import com.teamd.hungerexpressfooddelivery.utils.AppPreferences
import com.teamd.hungerexpressfooddelivery.utils.internet.InternetConnectivityObserver
import com.teamd.hungerexpressfooddelivery.utils.internet.interfces.ConnectivityObserver
import com.teamd.hungerexpressfooddelivery.utils.permission.PermissionManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {
        fun start(context: Activity) {
            context.startActivity(Intent(context, MainActivity::class.java))
            context.finish()
        }
    }

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var connectivityObserver: ConnectivityObserver
    private lateinit var splashScreen: SplashScreen
    private lateinit var permissionManager: PermissionManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashScreen = installSplashScreen()
        initView()
    }

    private fun initView() {
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        // Initialize the PermissionManager
        permissionManager = PermissionManager(this)

        // Request location permission
        permissionManager.requestLocationPermission()
        networkObserver()
    }

    private fun networkObserver() {
        connectivityObserver = InternetConnectivityObserver(applicationContext)
        val defaultStatus = ConnectivityObserver.InternetStatus.Available
        // Observe network status
        lifecycleScope.launch {
            connectivityObserver.observe()
                .onStart {
                    emit(defaultStatus)
                    showNetworkBackLayout()
                }
                .collect { status ->
                    when (status) {
                        ConnectivityObserver.InternetStatus.Available -> {
                            // Network is available
                            if(AppPreferences.isUserLoggedIn) {
                                NavActivity.start(this@MainActivity)
                            } else {
                                showMainActivity()
                            }
                        }

                        ConnectivityObserver.InternetStatus.Unavailable -> {
                            // Network is unavailable
                            Toast.makeText(
                                this@MainActivity,
                                "Network is unavailable",
                                Toast.LENGTH_LONG
                            ).show()
                            // Handle no network scenario, if needed
                            showNetworkBackLayout()
                        }

                        ConnectivityObserver.InternetStatus.Losing -> {
                            // Network is losing
                            Toast.makeText(
                                this@MainActivity,
                                "Please Check Your Intent Connection",
                                Toast.LENGTH_LONG
                            )
                                .show()
                            // Handle losing network, if needed
                            showNetworkBackLayout()
                        }

                        ConnectivityObserver.InternetStatus.Lost -> {
                            // Network is lost
                            Toast.makeText(this@MainActivity, "Network is lost", Toast.LENGTH_LONG)
                                .show()
                            // Handle lost network, if needed
                            showNetworkBackLayout()
                        }
                    }
                }
        }
    }

    private fun showMainActivity() {
        mainBinding.networkConnectionLayout.lostLayout.visibility = View.GONE
        mainBinding.mainLayout.visibility = View.VISIBLE
    }

    private fun showNetworkBackLayout() {
        mainBinding.mainLayout.visibility = View.GONE
        mainBinding.networkConnectionLayout.lostLayout.visibility = View.VISIBLE
    }
}