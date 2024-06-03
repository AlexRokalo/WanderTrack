package com.ar.wandertrack.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ar.wandertrack.presentation.theme.WanderTrackTheme
import com.ar.wandertrack.presentation.ui.authorization.login.LoginScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WanderTrackTheme {
                LoginScreen()
            }
        }
    }
}

