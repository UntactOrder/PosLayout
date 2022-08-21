package io.github.untactorder.myapplication.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import io.github.untactorder.myapplication.R

/**
 * @see <a href="URL">https://kotlinworld.com/211</a>
 */
val NanumBarunGothic = FontFamily(
    Font(R.font.nanumbarungothic, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.nanumbarungothicbold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.nanumbarungothiclight, FontWeight.Light, FontStyle.Normal),
    Font(R.font.nanumbarungothicultralight, FontWeight.ExtraLight, FontStyle.Normal),
)

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
