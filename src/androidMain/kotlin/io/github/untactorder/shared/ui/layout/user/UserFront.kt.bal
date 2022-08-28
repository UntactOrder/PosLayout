package io.github.untactorder.shared.ui.layout.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.untactorder.*
import io.github.untactorder.shared.ui.theme.UntactOrderApplicationTheme
import io.github.untactorder.shared.ui.theme.getSignature
import kotlin.random.Random


@Preview(name = "MainView", showBackground = true)
@Composable
fun UserFront() {
    val conStat: MutableState<Int> = rememberSaveable { mutableStateOf(0) }
    UntactOrderApplicationTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 20.dp),
            color = MaterialTheme.colorScheme.getSignature().background
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Greeting2("Android" + Random.nextInt())
                ShowAge(conStat)
                ShowCard()
                CreateCircle(conStat)
                Image(painterResource(id = R.drawable.ic_linear_logo), contentDescription = "Linear Logo")
            }
        }
    }
}
