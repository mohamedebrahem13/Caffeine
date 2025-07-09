package com.caffeine.ui.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.caffeine.R
import com.caffeine.android.theme.TextDarkGray
import com.caffeine.ui.composable.ButtonWithIcon
import com.caffeine.ui.composable.TopBarSection

@Composable
fun HomeIntroScreen(onBringCoffeeClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBarSection( modifier = Modifier.padding(top = 56.dp, bottom = 24.dp,start = 16.dp, end = 16.dp))
        GhostAndTextSection()
        ButtonWithIcon(
            text = stringResource(R.string.bring_my_coffee),
            icon = painterResource(id = R.drawable.coffee_02),
            onClick = {
                onBringCoffeeClick() }
        )
    }
}

@Composable
fun GhostAndTextSection() {
    val infiniteTransition = rememberInfiniteTransition(label = "ghost_and_shadow_anim")
    val ghostOffsetY by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = -32f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 800, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "ghost_offset"
    )

    val shadowOffsetY by infiniteTransition.animateFloat(
        initialValue = -12f,
        targetValue = 12f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 800, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "shadow_offset"
    )

    val shadowAlpha by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 800, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "shadow_alpha"
    )

    val starAlpha by infiniteTransition.animateFloat(
        initialValue = 0.12f,
        targetValue = 0.87f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 400),
            repeatMode = RepeatMode.Reverse
        )
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.wrapContentWidth()
        ) {
            Icon(
                painter = painterResource(R.drawable.star),
                contentDescription = "Stars",
                tint = Color.Black.copy(alpha = starAlpha),
                modifier = Modifier
                    .padding(top = 6.dp)
                    .size(16.dp)
                    .align(Alignment.TopEnd)
            )

            Icon(
                painter = painterResource(R.drawable.star),
                contentDescription = "Stars",
                tint = Color.Black.copy(alpha = starAlpha),
                modifier = Modifier
                    .padding(bottom = 45.dp, start = 10.dp)
                    .size(16.dp)
                    .align(Alignment.CenterStart)
            )
            Icon(
                painter = painterResource(R.drawable.star),
                contentDescription = stringResource(R.string.stars),
                tint = Color.Black.copy(alpha = starAlpha),
                modifier = Modifier
                    .size(16.dp)
                    .align(Alignment.BottomEnd)
            )

            Text(
                text = stringResource(R.string.hocus_pocus_i_need_coffee_to_focus),
                style = MaterialTheme.typography.displayLarge,
                textAlign = TextAlign.Center,
                color = TextDarkGray.copy(alpha = 0.87f)
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 33.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.coffee_ghost),
                contentDescription = stringResource(R.string.ghost_with_coffee),
                modifier = Modifier
                    .graphicsLayer {
                        translationY = ghostOffsetY
                    }
            )

            Image(
                painter = painterResource(R.drawable.goast_shadow),
                contentDescription = "Ghost Shadow",
                modifier = Modifier
                    .padding(bottom = 58.dp)
                    .graphicsLayer {
                        translationY = shadowOffsetY
                        alpha = shadowAlpha
                    }
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun HomeIntroScreenPreview() {
    HomeIntroScreen(
        onBringCoffeeClick = {}
    )
}