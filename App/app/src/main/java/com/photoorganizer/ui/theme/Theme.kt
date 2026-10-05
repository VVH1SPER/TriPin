package com.photoorganizer.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColors = darkColorScheme(
    primary = androidx.compose.ui.graphics.Color(0xFF8AB4F8),
    secondary = androidx.compose.ui.graphics.Color(0xFFB9D1FF),
    tertiary = androidx.compose.ui.graphics.Color(0xFF9ED7C3)
)

private val LightColors = lightColorScheme(
    primary = androidx.compose.ui.graphics.Color(0xFF2563EB),
    secondary = androidx.compose.ui.graphics.Color(0xFF60A5FA),
    tertiary = androidx.compose.ui.graphics.Color(0xFF34D399)
)

@Composable
fun PhotoOrganizerTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
