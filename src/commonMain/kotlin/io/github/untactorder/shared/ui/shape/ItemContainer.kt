package io.github.untactorder.shared.ui.shape

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalHapticFeedback
//import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
//import com.halilibo.richtext.markdown.Markdown
//import com.halilibo.richtext.ui.RichTextStyle
//import com.halilibo.richtext.ui.material3.Material3RichText
//import com.halilibo.richtext.ui.string.RichTextStringStyle
import io.github.untactorder.shared.ui.theme.*

//import java.text.DecimalFormat


@Composable
fun QuantitySelector(
    value: MutableState<Int> = rememberSaveable { mutableStateOf(0) },
    componentSize: Int = 34,
    fontSize: Int = 21,
    verticalPadding: Float = 0f,
    horizontalPadding: Float = 0f,
    contentsPadding: Float = 1.5f,
    fontFamily: FontFamily = NanumBarunGothic,
    textPadding: PaddingValues = PaddingValues(0.dp, 2.5.dp, 0.dp, 0.dp),
    onMinusClick: (Int) -> Unit = {}, onPlusClick: (Int) -> Unit = {}
) {
    val signatureColors = MaterialTheme.colorScheme.getSignature()
    val buttonModifier = Modifier
        .padding(contentsPadding.dp)
        .size(componentSize.dp)
    Row(modifier = Modifier
        .padding(
            vertical = UntactOrderElevation + (verticalPadding + contentsPadding).dp,
            horizontal = UntactOrderElevation + (horizontalPadding + contentsPadding).dp)
    ) {
        val underFlowOccur = remember { mutableStateOf(false) }
        val overFlowOccur = remember { mutableStateOf(false) }
        if (underFlowOccur.value) {
            //ToastView("0개 아래로는 주문할 수 없어요", AndroidToastType.warning)
            underFlowOccur.value = false
        }
        if (overFlowOccur.value) {
            //ToastView("1000개 이상으로는 주문할 수 없어요", AndroidToastType.error)
            overFlowOccur.value = false
        }
        CircleWithCenteredText(
            modifier = buttonModifier,
            colors = ButtonDefaults.buttonColors(containerColor = signatureColors.backBoard),
            contentPadding = textPadding,
            textValue = "-",
            textStyle = TextStyle(
                fontSize = fontSize.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                color = signatureColors.text)
        ) {
            if (value.value > 0) {
                value.value--
                onMinusClick(value.value)
            } else {
                underFlowOccur.value = true
            }
        }
        CircleWithCenteredText(
            modifier = buttonModifier,
            colors = ButtonDefaults.buttonColors(containerColor = signatureColors.text),
            contentPadding = textPadding,
            textValue = value,
            textStyle = TextStyle(
                fontSize = fontSize.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.ExtraLight,
                color = signatureColors.backBoard)
        )
        CircleWithCenteredText(
            modifier = buttonModifier,
            colors = ButtonDefaults.buttonColors(containerColor = signatureColors.backBoard),
            contentPadding = textPadding,
            textValue = "+",
            textStyle = TextStyle(
                fontSize = fontSize.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                color = signatureColors.text)
        ) {
            if (value.value < 1000) {
                value.value++
                onPlusClick(value.value)
            } else {
                overFlowOccur.value = true
            }
        }
    }
}

data class ItemType(val index: Int, val name: String)

data class ItemInfo(
    val id: Int, val name: String, val price: Double, val type: ItemType,
    val photoUrl: String, val description: String, val ingredient: String,
    val hashTag: String, val pinned: Boolean, val soldOut: Boolean)


private val descriptionForPreview = """
    이 라면은 흠 뭐라고 설명 해야 될까? 일단 맛있는거임. 설명 끝.
    * Unordered list can use asterisks
    - Or minuses
    + Or pluses
    ---
    ```javascript
    var s = "code blocks use monospace font";
    alert(s);
    ```
    Markdown | Table | Extension
    --- | --- | ---
    *renders* | `beautiful images` | ![random image](https://picsum.photos/seed/picsum/400/400 "Text 1")
    1 | 2 | 3
    > Blockquotes are very handy in email to emulate reply text.
    > This line is part of the same quote.
    """.trimIndent()

@Composable
fun ItemContainer(
    itemInfo: ItemInfo = ItemInfo(
        1, "치즈와 계란을 넣어 만든 라면이다냥 (안성탕면)", 12000.0,
        ItemType(1, "라면류"), "", descriptionForPreview, "재료 흠",
        "#인기있는 #맛집", pinned = true, soldOut = false),
    iso4217: String = "\\",
    componentSize: Int = 34, fontSize: Float = 15f, imageRatio: Float = 1.32f,
    margin: Float = 10f,
    onClick: () -> Unit = {}
) {
    val imageHeight = componentSize * 4.5
    val imageWidth = imageHeight * imageRatio
    val padding = 1.5f
    //val decFormat = DecimalFormat("#,###")
    val haptic = LocalHapticFeedback.current
    val signatureColors = MaterialTheme.colorScheme.getSignature()
    Column(
        modifier = Modifier
            .padding(UntactOrderElevation)
            .shadow(
                elevation = UntactOrderElevation,
                shape = RoundedCornerShape(UntactOrderRoundRadius + 20.dp),
                clip = true
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(color = signatureColors.ripple)
            ) {
                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                onClick()
            }
            .background(ItemListColors[1])
    ) {
        /*Image(
            painterResource(id = MR.images.tmp),
            contentDescription = "image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(margin.dp)
                .size(width = imageWidth.dp, height = imageHeight.dp)
                .shadow(
                    elevation = UntactOrderElevation,
                    shape = RoundedCornerShape(UntactOrderRoundRadius + 13.dp),
                    clip = true)
                .fillMaxWidth())*/
        ResponsiveText(
            modifier = Modifier.padding(horizontal = margin.dp).width(imageWidth.dp),
            text = itemInfo.name,
            textStyle = TextStyle(
                color = signatureColors.text,
                fontSize = (fontSize * 2).sp,
                fontFamily = NanumBarunGothic,
                fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Start,
            maxLines = 2)
        ResponsiveText(
            modifier = Modifier
                .padding(horizontal = margin.dp, vertical = (margin / 2.0).dp)
                .width(imageWidth.dp),
            text = "자세한 메뉴 정보를 보려면 여기를 누르세요!",
            textStyle = TextStyle(
                color = signatureColors.text,
                fontSize = (fontSize / 1.35).sp,
                fontFamily = NanumBarunGothic,
                fontWeight = FontWeight.ExtraLight),
            textAlign = TextAlign.End,
            maxLines = 2)
        Text(
            text = itemInfo.description,
            modifier = Modifier.padding(margin.dp).width(imageWidth.dp),
            color = signatureColors.backBoard,
            fontSize = fontSize.sp,
            fontFamily = NanumBarunGothic,
            fontWeight = FontWeight.Light,
            overflow = TextOverflow.Ellipsis,
            maxLines = 5)
        if (itemInfo.hashTag.isNotEmpty()) {
            Text(
                text = itemInfo.hashTag,
                modifier = Modifier
                    .width((imageWidth + margin * 2).dp)
                    .padding(horizontal = margin.dp),
                color = signatureColors.text,
                fontSize = (fontSize / 1.1).sp,
                fontFamily = NanumBarunGothic,
                fontWeight = FontWeight.ExtraLight)
        }
        ResponsiveText(
            modifier = Modifier
                .absolutePadding(margin.dp, top = margin.dp, margin.dp, 0.dp)
                .width(imageWidth.dp),
            text = "$iso4217${itemInfo.price}",//"$iso4217${decFormat.format(itemInfo.price)}",
            textStyle = TextStyle(
                color = signatureColors.text,
                fontSize = (fontSize * 1.5).sp,
                fontFamily = NanumBarunGothic,
                fontWeight = FontWeight.Normal),
            textAlign = TextAlign.Start,
            maxLines = 2)
        Row(
            Modifier.width((imageWidth + margin * 2).dp).absolutePadding(bottom = margin.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val horizontalPad = 2.0f
            Row(Modifier
                .padding(UntactOrderElevation + padding.dp)
                .weight(1.0f, true)
            ) {
                CircleWithCenteredText(
                    modifier = Modifier
                        .height((componentSize + padding).dp)
                        .padding(vertical = padding.dp, horizontal = (padding + horizontalPad).dp),
                    shape = RoundedCornerShape(UntactOrderRoundRadius + 20.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = signatureColors.text),
                    contentPadding = PaddingValues(7.dp, 5.2.dp, 8.dp, 5.2.dp),
                    textValue = "주문 량",
                    textStyle = TextStyle(
                        color = signatureColors.backBoard,
                        fontSize = fontSize.sp,
                        fontFamily = NanumBarunGothic,
                        fontWeight = FontWeight.ExtraLight),
                    maxLines = 2
                )
            }
            QuantitySelector(
                value = rememberSaveable { mutableStateOf(0) },
                componentSize = componentSize,
                horizontalPadding = horizontalPad,
                fontSize  = 21,
                contentsPadding  = 1.5f,
                fontFamily = NanumBarunGothic,
                textPadding = PaddingValues(0.dp, 2.5.dp, 0.dp, 0.dp),
                onMinusClick = {},
                onPlusClick = {}
            )
        }
    }
}


@Composable
fun DetailedItemContainer(
    itemInfo: ItemInfo = ItemInfo(
        1, "치즈와 계란을 넣어 만든 라면이다냥 (안성탕면)", 12000.0,
        ItemType(1, "라면류"), "", descriptionForPreview, "재료 흠",
        "#인기있는 #맛집", pinned = true, soldOut = false),
    iso4217: String = "\\",
    componentSize: Int = 34, fontSize: Float = 15f, imageRatio: Float = 1.25f,
    padding: Float = 10f, margin: Float = 20f,
    onClick: () -> Unit = {}
) {
    val imageHeight = componentSize * 4.5
    val imageWidth = imageHeight * imageRatio
    val selectorPadding = 1.5f
    //val decFormat = DecimalFormat("#,###")
    val haptic = LocalHapticFeedback.current
    val signatureColors = MaterialTheme.colorScheme.getSignature()
    Column {
        /*Image(
            painterResource(id = MR.images.tmp),
            contentDescription = "image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(padding.dp, padding.dp, padding.dp, 0.dp)
                .shadow(
                    elevation = UntactOrderElevation,
                    shape = RoundedCornerShape(UntactOrderRoundRadius),
                    clip = true)
                .fillMaxWidth()
                .aspectRatio(imageRatio))*/
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding.dp)
                .shadow(
                    elevation = UntactOrderElevation,
                    shape = RoundedCornerShape(UntactOrderRoundRadius),
                    clip = true
                )
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(color = signatureColors.ripple)
                ) {
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    onClick()
                }
                .background(ItemListColors[1])
        ) {
            ResponsiveText(
                modifier = Modifier
                    .padding(margin.dp, margin.dp, margin.dp, (margin / 4.0).dp)
                    .fillMaxWidth(),
                text = itemInfo.name,
                textStyle = TextStyle(
                    color = signatureColors.text,
                    fontSize = (fontSize * 2).sp,
                    fontFamily = NanumBarunGothic,
                    fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Start,
                maxLines = 2)
            ResponsiveText(
                modifier = Modifier
                    .padding(margin.dp, (margin / 4.0).dp, margin.dp, (margin / 4.0).dp)
                    .fillMaxWidth(),
                text = "아무 곳이나 눌러서 주문 화면으로 돌아갈 수 있어요!",
                textStyle = TextStyle(
                    color = signatureColors.text,
                    fontSize = (fontSize / 1.35).sp,
                    fontFamily = NanumBarunGothic,
                    fontWeight = FontWeight.ExtraLight),
                textAlign = TextAlign.End,
                maxLines = 2)
            /*val span = SpanStyle(
                color = signatureColors.text,
                fontSize = 21.sp,
                fontWeight = FontWeight.ExtraLight,
                fontFamily = NanumBarunGothic
            )*/


            /*
            Material3RichText(
                Modifier
                    .padding(margin.dp, (margin / 2.0).dp)
                    .fillMaxWidth(),
                /*style = RichTextStyle(
                    paragraphSpacing = TextUnit.Unspecified,
                    stringStyle = RichTextStringStyle(
                        boldStyle = span, span, span, span, span, span, span, span
                    )
                )*/
            ) {
                Markdown(itemInfo.description)
            }


            */
            if (itemInfo.hashTag.isNotEmpty()) {
                Text(
                    text = itemInfo.hashTag,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = margin.dp),
                    color = signatureColors.text,
                    fontSize = (fontSize / 1.1).sp,
                    fontFamily = NanumBarunGothic,
                    fontWeight = FontWeight.ExtraLight)
            }
            ResponsiveText(
                modifier = Modifier
                    .absolutePadding(margin.dp, top = margin.dp, margin.dp, 0.dp)
                    .fillMaxWidth(),
                text = "$iso4217${itemInfo.price}",//"$iso4217${decFormat.format(itemInfo.price)}",
                textStyle = TextStyle(
                    color = signatureColors.text,
                    fontSize = (fontSize * 1.5).sp,
                    fontFamily = NanumBarunGothic,
                    fontWeight = FontWeight.Normal),
                textAlign = TextAlign.Start,
                maxLines = 2)
            Row(
                Modifier.width((imageWidth + margin * 2).dp).absolutePadding(bottom = margin.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val horizontalPad = 2.0f
                Row(Modifier
                    .padding(UntactOrderElevation + selectorPadding.dp)
                    .weight(1.0f, true)
                ) {
                    CircleWithCenteredText(
                        modifier = Modifier
                            .height((componentSize + selectorPadding).dp)
                            .padding(vertical = selectorPadding.dp, horizontal = (selectorPadding + horizontalPad).dp),
                        shape = RoundedCornerShape(UntactOrderRoundRadius + 20.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = signatureColors.text),
                        contentPadding = PaddingValues(7.dp, 5.2.dp, 8.dp, 5.2.dp),
                        textValue = "주문 량",
                        textStyle = TextStyle(
                            color = signatureColors.backBoard,
                            fontSize = fontSize.sp,
                            fontFamily = NanumBarunGothic,
                            fontWeight = FontWeight.ExtraLight),
                        maxLines = 2
                    )
                }
                QuantitySelector(
                    value = rememberSaveable { mutableStateOf(0) },
                    componentSize = componentSize,
                    horizontalPadding = horizontalPad,
                    fontSize  = 21,
                    contentsPadding  = 1.5f,
                    fontFamily = NanumBarunGothic,
                    textPadding = PaddingValues(0.dp, 2.5.dp, 0.dp, 0.dp),
                    onMinusClick = {},
                    onPlusClick = {}
                )
            }
        }
    }
}
