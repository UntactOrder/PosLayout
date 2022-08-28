package io.github.untactorder.shared.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable

@Composable
actual fun chooseColorScheme(darkTheme: Boolean, dynamicColor: Boolean): ColorScheme {
    return if (darkTheme) DarkColorScheme else LightColorScheme
}
