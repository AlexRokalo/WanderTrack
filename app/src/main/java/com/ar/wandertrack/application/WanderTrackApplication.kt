package com.ar.wandertrack.application

import android.app.Application
import com.google.firebase.FirebaseApp

class WanderTrackApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}