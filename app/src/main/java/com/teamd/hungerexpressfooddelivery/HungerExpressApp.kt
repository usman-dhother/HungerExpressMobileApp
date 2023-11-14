package com.teamd.hungerexpressfooddelivery

import android.app.Application
import com.teamd.hungerexpressfooddelivery.utils.AppPreferences
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HungerExpressApp : Application(){
    override fun onCreate() {
        super.onCreate()
        AppPreferences.init(applicationContext)
    }
}