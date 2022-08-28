package io.github.untactorder.shared.ui.shape

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.untactorder.shared.ui.theme.UntactOrderElevation

@Composable
fun CircleWithCenteredText(
    modifier: Modifier = Modifier
        .padding(UntactOrderElevation + 2.dp)
        .size(100.dp),
    shape: RoundedCornerShape = CircleShape,
    colors: ButtonColors = ButtonDefaults.buttonColors(containerColor = Color.Black),
    textValue: Any = rememberSaveable { mutableStateOf(10000) },
    textStyle: TextStyle = TextStyle(
        fontSize = 35.sp, fontWeight = FontWeight.ExtraLight, color = Color.White),
    contentPadding: PaddingValues = PaddingValues(0.dp, 0.dp, 0.dp, 0.dp),
    maxLines: Int = 1,
    onClick: () -> Unit = {}
) {
    val haptic = LocalHapticFeedback.current
    Button(
        modifier = modifier,
        shape = shape,
        colors = colors,
        elevation = ButtonDefaults.buttonElevation(defaultElevation = UntactOrderElevation),
        contentPadding = PaddingValues(0.dp),
        onClick = {
            haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
            onClick()
        }
    ) {
        ResponsiveText(
            modifier = Modifier.padding(contentPadding).wrapContentHeight(),
            text = if (textValue is MutableState<*>)
                textValue.value.toString() else textValue.toString(),
            textStyle = textStyle,
            color = textStyle.color,
            maxLines = maxLines
        )
    }
}
