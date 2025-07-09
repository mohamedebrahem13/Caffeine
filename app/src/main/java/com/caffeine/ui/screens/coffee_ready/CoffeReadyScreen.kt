package com.caffeine.ui.screens.coffee_ready

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.caffeine.R
import com.caffeine.android.theme.CoffeeBrown
import com.caffeine.android.theme.LightBackground
import com.caffeine.android.theme.TextDarkGray
import com.caffeine.android.theme.shadowColor
import com.caffeine.ui.composable.ButtonWithIcon
import com.caffeine.ui.screens.coffee_ready.composable.CoffeeSwitch

@Composable
fun CoffeeReadyScreen(
    onContinueClicked: () -> Unit,
    onBackClicked: () -> Unit
) {
    val animationState = remember { MutableTransitionState(false) }

    var isTakeAway by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        animationState.targetState = true
    }

    val transition = rememberTransition(animationState, "ReadyScreenTransition")

    val initialYOffset = (-350).dp

    val topSectionY by transition.animateDp(
        label = "TopOffset",
        transitionSpec = { tween(durationMillis = 800, delayMillis = 200) }
    ) { state ->
        if (state) (-50).dp else initialYOffset
    }

    val bottomButtonY by transition.animateDp(
        label = "BottomOffset",
        transitionSpec = { tween(durationMillis = 800, delayMillis = 200) }
    ) { state ->
        if (state) 0.dp else -initialYOffset
    }

    val contentAlpha by transition.animateFloat(
        label = "ContentAlpha",
        transitionSpec = { tween(durationMillis = 1000, delayMillis = 300) }
    ) { state ->
        if (state) 1f else 0f
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(top = 56.dp)
    ) {

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = 50.dp)
                .alpha(contentAlpha),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .size(width = 245.dp, height = 300.dp),
                    painter = painterResource(id = R.drawable.esspreso_empty),
                    contentDescription = stringResource(R.string.cup_body)
                )
                Image(
                    painter = painterResource(id = R.drawable.logo_coffee),
                    contentDescription = stringResource(R.string.coffee_cup),
                    modifier = Modifier
                        .size(64.dp)
                )
            }
            Row(
                modifier = Modifier.padding(top = 27.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CoffeeSwitch(
                    checked = isTakeAway,
                    onCheckedChange = { isTakeAway = it },
                )
                Text(
                    text = stringResource(R.string.take_away),
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextDarkGray.copy(alpha = 0.7f)
                )
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .offset(y = topSectionY),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp))
            Icon(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .background(
                        color = LightBackground,
                        shape = CircleShape
                    )
                    .padding(12.dp)
                    .align(Alignment.Start)
                    .clip(CircleShape)
                    .clickable(onClick = onBackClicked),
                painter = painterResource(R.drawable.cancel_01),
                contentDescription = null,
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .drawBehind {
                        val shadowRadius = 16.dp.toPx()
                        val offsetY = 6.dp.toPx()

                        val paint = Paint().asFrameworkPaint().apply {
                            isAntiAlias = true
                            color = android.graphics.Color.TRANSPARENT
                            setShadowLayer(shadowRadius, 0f, offsetY, shadowColor.toArgb())
                        }

                        drawIntoCanvas {
                            it.nativeCanvas.drawCircle(
                                center.x,
                                center.y,
                                size.minDimension / 2f,
                                paint
                            )
                        }
                    }
                    .background(CoffeeBrown, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.tick_02),
                    contentDescription = stringResource(R.string.ready),
                    tint = White.copy(alpha = 0.87f)
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = stringResource(R.string.your_coffee_is_ready_enjoy),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                color = Color(0xDE1F1F1F)
            )
            Image(
                modifier = Modifier
                    .padding(top = 56.dp)
                    .size(width = 260.dp, height = 69.dp),
                painter = painterResource(id = R.drawable.coffee_cover),
                contentDescription = stringResource(R.string.coffee_lid)
            )
        }

        ButtonWithIcon (
            modifier = Modifier
                .padding(top = 20.dp, bottom = 30.dp)
                .align(Alignment.BottomCenter)
                .offset(y = bottomButtonY),
            text = stringResource(R.string.take_snack),
            icon = painterResource(R.drawable.arrow_right_04),
            onClick = onContinueClicked
        )
    }
}
