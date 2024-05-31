package com.ar.wandertrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InitCard(name = "WanderTrack")
        }
    }
}

@Composable
fun InitCard(name: String) {
    Text(text = "Hi, $name")
}

@Preview
@Composable
fun PreviewInitCard() {
    InitCard(name = "WanderTrack")
}