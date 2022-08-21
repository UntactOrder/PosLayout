package io.github.untactorder.myapplication.ui.shape

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import com.shashank.sony.fancytoastlib.FancyToast


data class ToastType(val default: Int, val info: Int, val success: Int, val warning: Int, val error: Int, val retry: Int)

val AndroidToastType = ToastType(5, 4, 1, 2, 3, 6)

@Composable
fun ToastView(
    message: String = "Default Message",
    toastType: Int = AndroidToastType.default,
    briefly: Boolean = true) {
    val context = LocalContext.current  // Fetching the local context for using the Toast
    showToast(context, message, toastType, briefly)
}

fun showToast(
    context: Context,
    message: String = "Default Message",
    toastType: Int = AndroidToastType.default,
    briefly: Boolean = true) {
    FancyToast.makeText(context, message,
        if (briefly) FancyToast.LENGTH_SHORT else FancyToast.LENGTH_LONG,
        toastType, true).show()
}