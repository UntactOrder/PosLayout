package io.github.untactorder.myapplication.ui.layout.store

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.untactorder.myapplication.CreateCircle
import io.github.untactorder.myapplication.Greeting2
import io.github.untactorder.myapplication.R
import io.github.untactorder.myapplication.ShowAge
import io.github.untactorder.myapplication.ShowCard
import io.github.untactorder.myapplication.ui.shape.ItemContainer
import io.github.untactorder.myapplication.ui.shape.QuantitySelector
import io.github.untactorder.myapplication.ui.theme.UntactOrderApplicationTheme
import io.github.untactorder.myapplication.ui.theme.getSignature
import kotlin.random.Random


@Preview(showBackground = true)
@Composable
fun RecommendedItemGridView(){
    val list = (1..100).map { it.toString() }

    LazyVerticalGrid(
        columns = GridCells.Adaptive(128.dp),

        // content padding
        contentPadding = PaddingValues(
            start = 12.dp,
            top = 16.dp,
            end = 12.dp,
            bottom = 16.dp
        ),
        content = {
            items(list.size) { index ->
                Card(
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    Text(
                        text = list[index],
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ItemListView() {
    val list = listOf(
        "A", "B", "C", "D"
    ) + ((0..100).map { it.toString() })
    LazyRow(modifier = Modifier.fillMaxHeight()) {
        items(count = list.size, itemContent = { item ->
            println("COMPOSE: This get rendered $item")
            when (list[item]) {
                "A" -> {
                    Text(text = list[item], style = TextStyle(fontSize = 80.sp))
                }
                "B" -> {
                    Button(onClick = {}) {
                        Text(text = list[item], style = TextStyle(fontSize = 80.sp))
                    }
                }
                "C" -> {
                    //Do Nothing
                }
                "D" -> {
                    Text(text = list[item])
                }
                else -> {
                    Text(text = list[item], style = TextStyle(fontSize = 80.sp))
                }
            }
        })
    }
}


@Preview(name = "MenuSelectActivity", showBackground = true)
@Composable
fun ItemView() {
    val conStat: MutableState<Int> = rememberSaveable { mutableStateOf(0) }
    UntactOrderApplicationTheme {
        val scrollState = rememberScrollState()
        Surface(
            modifier = Modifier
                .verticalScroll(scrollState)
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.getSignature().background
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ItemContainer() {

                }
            }
        }
    }
}
