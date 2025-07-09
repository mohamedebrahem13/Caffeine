package com.caffeine.ui.screens.coffee_order

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.caffeine.R
import com.caffeine.android.theme.CoffeeBrown
import com.caffeine.android.theme.TextDarkGray
import com.caffeine.ui.composable.ButtonWithIcon
import com.caffeine.ui.screens.coffee_order.composable.AlmostDone
import com.caffeine.ui.screens.coffee_order.composable.Header
import com.caffeine.ui.screens.coffee_order.composable.SelectorsSection
import com.caffeine.ui.screens.coffee_order.viewmodel.CoffeeOrderViewModel
import com.caffeine.ui.screens.coffee_order.viewmodel.CoffeeStrength
import com.caffeine.ui.screens.coffee_order.viewmodel.CupSize
import com.caffeine.ui.utiles.noRippleClickable
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel


@Composable
fun CoffeeOrderScreen(
    onClickBack: () -> Unit,
    onNavigateToProcessing: () -> Unit,
    viewModel: CoffeeOrderViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    var showHeader by remember { mutableStateOf(false) }
    var showButton by remember { mutableStateOf(false) }

    val configuration = LocalConfiguration.current
    val screenHeightPx =
        with(LocalDensity.current) { configuration.screenHeightDp.dp.toPx().toInt() }

    val cupScale by animateFloatAsState(
        targetValue = state.selectedSize.scale,
        animationSpec = tween(400),
        label = "cupScale"
    )

    val logoSize by animateDpAsState(
        targetValue = when (state.selectedSize) {
            CupSize.SMALL -> 40.dp
            CupSize.MEDIUM, CupSize.LARGE -> 66.dp
        },
        animationSpec = tween(400),
        label = "logoSize"
    )

    LaunchedEffect(Unit) {
        showHeader = true
        showButton = true
    }

    if (state.isProcessing) {
        LaunchedEffect(Unit) {
            delay(3600L)
            onNavigateToProcessing()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.navigationBars)
        ) {
            item {
                Box(modifier = Modifier.padding(top = 56.dp)) {
                    AnimatedVisibility(
                        visible = !state.isProcessing && showHeader,
                        enter = slideInVertically(
                            initialOffsetY = { screenHeightPx },
                            animationSpec = tween(durationMillis = 700)
                        ),
                        exit = slideOutVertically { -it } + fadeOut()
                    ) {
                        Header(onClickBack)
                    }
                }
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = state.selectedSize.volumeText,
                        color = Color.Black.copy(alpha = 0.6f),
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.padding(start = 24.dp, top = 40.dp)
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 20.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CoffeeStrength.entries.forEach { strength ->
                            AnimatedCoffeeBeans(
                                isVisible = strength in state.animatedStrengths,
                                strength = strength
                            )
                        }

                        Image(
                            painter = painterResource(id = R.drawable.esspreso_empty),
                            contentDescription = null,
                            modifier = Modifier.scale(cupScale)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.logo_coffee),
                            contentDescription = null,
                            modifier = Modifier.size(logoSize)
                        )
                    }
                }
            }

            item {
                Crossfade(targetState = state.isProcessing) { processing ->
                    if (!processing) {
                        SelectorsSection(
                            selectedSize = state.selectedSize,
                            onSizeSelected = viewModel::selectSize,
                            selectedStrength = state.selectedStrength,
                            onStrengthSelected = viewModel::selectStrength,
                        )
                    } else {
                        AlmostDone(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 200.dp)
                        )
                    }
                }
            }

            item {
                AnimatedVisibility(
                    visible = showButton && !state.isProcessing,
                    enter = slideInVertically(
                        initialOffsetY = { it + 500 },
                        animationSpec = tween(durationMillis = 700)
                    )
                ) {
                    ButtonWithIcon(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp, vertical = 32.dp),
                        text = "Continue",
                        icon = painterResource(R.drawable.arrow_right_04),
                        onClick = viewModel::startProcessing
                    )
                }
            }
        }

    }
}
@Composable
fun AnimatedCoffeeBeans(
    isVisible: Boolean,
    strength: CoffeeStrength
) {
    val transition = updateTransition(targetState = isVisible)

    val animationSpec: FiniteAnimationSpec<Float> = spring(
        dampingRatio = 0.6f,
        stiffness = Spring.StiffnessVeryLow
    )
    val animationSpecDp: FiniteAnimationSpec<Dp> = spring(
        dampingRatio = 0.6f,
        stiffness = Spring.StiffnessVeryLow
    )

    val scale by transition.animateFloat(
        label = "BeansScale",
        transitionSpec = { animationSpec }
    ) { visible ->
        if (visible) 0.8f else 1.5f
    }

    val yOffset by transition.animateDp(
        label = "BeansYOffset",
        transitionSpec = { animationSpecDp }
    ) { visible ->
        if (visible) 0.dp else (-300).dp
    }

    val alpha by transition.animateFloat(
        label = "BeansAlpha",
        transitionSpec = { animationSpec }
    ) { visible ->
        if (visible) strength.targetAlpha else 0f
    }

    Image(
        painter = painterResource(id = R.drawable.coffee_seeds),
        contentDescription = "Animated Coffee Beans",
        modifier = Modifier
            .size(100.dp)
            .graphicsLayer {
                this.scaleX = scale
                this.scaleY = scale
                this.translationY = yOffset.toPx()
                this.alpha = alpha
            }
    )
}

@Composable
fun SizeSelector(
    modifier: Modifier = Modifier,
    selectedSize: CupSize,
    onSizeSelected: (CupSize) -> Unit
) {
    val options = CupSize.entries.toTypedArray()

    Row(
        modifier = modifier
            .wrapContentSize()
            .clip(RoundedCornerShape(100.dp))
            .background(Color(0xFFF5F5F5))
            .padding(horizontal = 8.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        options.forEach { size ->
            val isSelected = selectedSize == size

            val backgroundColor by animateColorAsState(
                targetValue = if (isSelected) CoffeeBrown else Color.Transparent,
                animationSpec = tween(durationMillis = 300),
                label = "SizeOptionBackground"
            )

            val textColor by animateColorAsState(
                targetValue = if (isSelected) Color.White.copy(alpha = 0.87f) else TextDarkGray.copy(
                    alpha = 0.8f
                ),
                animationSpec = tween(durationMillis = 300),
                label = "SizeOptionTextColor"
            )

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(backgroundColor)
                    .noRippleClickable { onSizeSelected(size) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = size.displayName,
                    color = textColor,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun StrengthSelector(
    selectedStrength: CoffeeStrength,
    onStrengthSelected: (CoffeeStrength) -> Unit
) {
    val options = CoffeeStrength.entries.toTypedArray()

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            modifier = Modifier
                .width(152.dp)
                .clip(RoundedCornerShape(100.dp))
                .background(Color(0xFFF5F5F5))
                .padding(horizontal = 8.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            options.forEach { strength ->
                val isSelected = (selectedStrength == strength)

                val alpha by animateFloatAsState(
                    targetValue = if (isSelected) 1f else 0f,
                    animationSpec = tween(durationMillis = 600),
                    label = "StrengthAlpha"
                )

                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.Transparent)
                        .noRippleClickable { onStrengthSelected(strength) },
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .alpha(alpha)
                            .shadow(8.dp, shape = CircleShape)
                            .size(40.dp)
                            .background(Color(0xFF7C351B), shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.coffee_beans),
                            contentDescription = null,
                            tint = Color(0xDEFFFFFF),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .width(152.dp)
                .padding(top = 2.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            options.forEach { strength ->
                Text(
                    text = strength.displayName,
                    color = Color(0x991F1F1F),
                    fontSize = 10.sp
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCoffeeOrderScreen() {
    CoffeeOrderScreen({}, {})
}