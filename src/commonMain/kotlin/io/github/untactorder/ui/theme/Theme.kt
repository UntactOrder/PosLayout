package io.github.untactorder.myapplication.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat

private val DarkColorScheme = darkColorScheme(
    primary = SignatureColorsDark.red,
    onPrimary = SignatureColorsDark.text,
    secondary = SignatureColorsDark.yellow,
    onSecondary = SignatureColorsDark.text,
    tertiary = SignatureColorsDark.purple,
    onTertiary = SignatureColorsDark.text,
    background = SignatureColorsDark.background,
    onBackground = SignatureColorsDark.background,
    surface = SignatureColorsDark.background,
    onSurface = SignatureColorsDark.background
)

private val LightColorScheme = lightColorScheme(
    primary = SignatureColorsLight.red,
    onPrimary = SignatureColorsLight.text,
    secondary = SignatureColorsLight.yellow,
    onSecondary = SignatureColorsLight.text,
    tertiary = SignatureColorsLight.purple,
    onTertiary = SignatureColorsLight.text,
    background = SignatureColorsLight.background,
    onBackground = SignatureColorsLight.background,
    surface = SignatureColorsLight.background,
    onSurface = SignatureColorsLight.background
)

@Composable
fun ColorScheme.getSignature(darkTheme: Boolean = isSystemInDarkTheme()): SignatureColors {
    return if (darkTheme) SignatureColorsDark else SignatureColorsLight
}

val UntactOrderRoundRadius = 24.dp
val UntactOrderElevation = 7.dp

@Composable
fun UntactOrderApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colorScheme.primary.toArgb()
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = {
            CompositionLocalProvider(LocalRippleTheme provides UntactOrderRippleTheme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.getSignature().background) {
                    content()
                }
            }
        }
    )
}

/**
 * @see <a href="URL">https://stackoverflow.com/questions/67265345/modify-ripple-color-of-iconbutton-in-jetpack-compose</a>
 */
private object UntactOrderRippleTheme : RippleTheme {
    // Here you should return the ripple color you want
    // and not use the defaultRippleColor extension on RippleTheme.
    // Using that will override the ripple color set in DarkMode
    // or when you set light parameter to false
    @Composable
    override fun defaultColor(): Color = MaterialTheme.colorScheme.getSignature().ripple

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleTheme.defaultRippleAlpha(
        Color.Black,
        lightTheme = !isSystemInDarkTheme()
    )
}
