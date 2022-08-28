package io.github.untactorder.shared.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

actual val NanumBarunGothic = FontFamily(
    Font(NanumBarunGothicRegular.fontResourceId, FontWeight.Normal, FontStyle.Normal),
    Font(NanumBarunGothicBold.fontResourceId, FontWeight.Bold, FontStyle.Normal),
    Font(NanumBarunGothicLight.fontResourceId, FontWeight.Light, FontStyle.Normal),
    Font(NanumBarunGothicUltraLight.fontResourceId, FontWeight.ExtraLight, FontStyle.Normal),
)
