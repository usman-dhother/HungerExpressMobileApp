package com.teamd.hungerexpressfooddelivery.utils.permission

import android.Manifest
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.CompositeMultiplePermissionsListener
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

class PermissionManager(private val context: Context) {

    private var locationPermissionListener = object : MultiplePermissionsListener {
        override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
            // Check if all permissions were granted
            if (report?.areAllPermissionsGranted() == true) {
                // Location permission granted, you can now use it
            } else {
                // Handle the case where some or all permissions were denied
            }
        }

        override fun onPermissionRationaleShouldBeShown(permissions: MutableList<PermissionRequest>?, token: PermissionToken?) {
            // This is called when permissions are denied with a rationale.
            // You can show a dialog or explanation here.
            token?.continuePermissionRequest()
        }
    }

    fun requestLocationPermission() {
        Dexter.withContext(context)
            .withPermissions(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            .withListener(locationPermissionListener)
            .check()
    }
}