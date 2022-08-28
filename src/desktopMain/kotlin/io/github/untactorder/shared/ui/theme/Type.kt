package io.github.untactorder.shared.ui.theme

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font

actual val NanumBarunGothic = FontFamily(
    Font(NanumBarunGothicRegular.filePath, FontWeight.Normal, FontStyle.Normal),
    Font(NanumBarunGothicBold.filePath, FontWeight.Bold, FontStyle.Normal),
    Font(NanumBarunGothicLight.filePath, FontWeight.Light, FontStyle.Normal),
    Font(NanumBarunGothicUltraLight.filePath, FontWeight.ExtraLight, FontStyle.Normal),
)
