package io.github.untactorder.shared.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import io.github.untactorder.shared.MR

/**
 * @see <a href="URL">https://kotlinworld.com/211</a>
 */
expect val NanumBarunGothic: FontFamily

val NanumBarunGothicRegular = MR.fonts.NanumBarunGothic.regular
val NanumBarunGothicBold = MR.fonts.NanumBarunGothic.bold
val NanumBarunGothicLight = MR.fonts.NanumBarunGothic.light
val NanumBarunGothicUltraLight = MR.fonts.NanumBarunGothic.ultraLight

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )/*,
    titleLarge = TextStyle(
        fontFamily = NanumBarunGothic,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = NanumBarunGothic,
        fontWeight = FontWeight.ExtraLight,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )*/
)
