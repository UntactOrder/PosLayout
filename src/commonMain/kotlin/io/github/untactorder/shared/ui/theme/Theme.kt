package io.github.untactorder.shared.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

val DarkColorScheme = darkColorScheme(
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

val LightColorScheme = lightColorScheme(
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
expect fun chooseColorScheme(darkTheme: Boolean = isSystemInDarkTheme(), dynamicColor: Boolean = true): ColorScheme

@Composable
fun UntactOrderApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = chooseColorScheme(darkTheme, dynamicColor),
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
