package com.ar.wandertrack.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun WanderTrackTheme(
    isSystemInTheDark: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        // Configure dynamic color for Android 12 and above
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (isSystemInTheDark) {
                dynamicDarkColorScheme(context)
            } else {
                dynamicLightColorScheme(context)
            }
        }

        isSystemInTheDark -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current

    if (view.isInEditMode.not()) {
        // Change the status bar color to match the theme
        SideEffect {
            val window = (view.context as Activity).window

            window.statusBarColor = Color.Transparent.toArgb()
            WindowCompat
                .getInsetsController(window, view)
                .isAppearanceLightStatusBars = isSystemInTheDark.not()
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

private val DarkColorScheme = darkColorScheme(
    primary = blackPrimaryColor,
    secondary = blueSecondaryColor,
    tertiary = greyTertiaryColor,
    background = Color.Black,
    onBackground = Color.White
)


private val LightColorScheme = darkColorScheme(
    primary = blackPrimaryColor,
    secondary = blueSecondaryColor,
    tertiary = greyTertiaryColor,
    background = Color.White,
    onBackground = blackPrimaryColor
)