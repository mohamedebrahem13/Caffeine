package com.caffeine.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import com.caffeine.R
import com.caffeine.android.theme.DarkGrayText
import com.caffeine.android.theme.LightGrayText
import com.caffeine.android.theme.SubtleText
import com.caffeine.ui.composable.ButtonWithIcon
import com.caffeine.ui.composable.TopBarSection
import kotlin.math.absoluteValue


val coffeeDrinks = listOf(
    CoffeeDrink("Espresso", R.drawable.espresso),
    CoffeeDrink("Macchiato", R.drawable.macchiato),
    CoffeeDrink("Latte", R.drawable.latte),
    CoffeeDrink("Black", R.drawable.black)
)

@Composable
fun CoffeePagerScreen(
    onClick: (CoffeeDrink) -> Unit
) {
    val pagerState = rememberPagerState(initialPage = 1) {
        coffeeDrinks.size
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.Start,
    ) {
        TopBarSection(
            modifier = Modifier.padding(
                top = 56.dp,
                bottom = 16.dp,
                start = 16.dp,
                end = 16.dp
            )
        )

        Text(
            modifier = Modifier.padding(start = 16.dp, top = 16.dp),
            text = "Good Morning",
            style = MaterialTheme.typography.displayMedium,
            color = LightGrayText
        )

        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = "Hamsa ☀",
            style = MaterialTheme.typography.displayMedium,
            color = DarkGrayText
        )

        Text(
            modifier = Modifier.padding(start = 16.dp, bottom = 56.dp),
            text = "What would you like to drink today?",
            style = MaterialTheme.typography.bodyLarge,
            color = SubtleText
        )

        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 100.dp),
            pageSpacing = 20.dp
        ) { page ->
            PagerItem(
                drink = coffeeDrinks[page],
                pagerState = pagerState,
                page = page
            )
        }
        Spacer(Modifier.weight(1f))
        ButtonWithIcon(
            modifier = Modifier
                .padding(bottom = 50.dp)
                .align(Alignment.CenterHorizontally),
            text = "Continue",
            icon = painterResource(R.drawable.arrow_right_04),
            onClick = { onClick(coffeeDrinks[pagerState.currentPage]) }
        )
    }
}

@Composable
fun PagerItem(drink: CoffeeDrink, pagerState: PagerState, page: Int) {
    val pageOffset = (
            (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
            ).absoluteValue

    val scale = lerp(
        start = 1f,
        stop = 0.60f,
        fraction = pageOffset
    )

    val translationY = lerp(
        start = 0f,
        stop = 150f,
        fraction = pageOffset
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    )
    {

        Image(
            modifier = Modifier
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    this.translationY = translationY
                }
                .size(width = 199.dp, height = 244.dp),
            painter = painterResource(id = drink.imageRes),
            contentDescription = drink.name,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = drink.name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

data class CoffeeDrink(val name: String, val imageRes: Int)


@Preview(showBackground = true)
@Composable
fun PreviewCoffeePagerScreen() {
    CoffeePagerScreen {}
}