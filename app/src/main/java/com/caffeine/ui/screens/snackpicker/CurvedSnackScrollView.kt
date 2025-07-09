package com.caffeine.ui.screens.snackpicker

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.caffeine.android.theme.LightBackground
import kotlin.math.abs

@Composable
fun CurvedSnackScrollPager(
    snacks: List<Int>,
    onSnackClick: () -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { snacks.size })

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        VerticalPager(
            state = pagerState,
            modifier = Modifier
                .width(260.dp)
                .fillMaxHeight()
                .offset(x = (-60).dp),
            pageSpacing = (-100).dp,
            contentPadding = PaddingValues(vertical = 200.dp),
            userScrollEnabled = true
        ) { page ->

            val pageOffset = ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction)
                .coerceIn(-1f, 1f)

            val scale = 1f - 0.15f * abs(pageOffset)
            val alpha = 1f - 0.3f * abs(pageOffset)
            val rotationX = pageOffset * 50f
            val translationX = pageOffset * 40f
            val translationY = pageOffset * 15f

            val density = LocalDensity.current.density

            Box(
                modifier = Modifier
                    .width(260.dp)
                    .height(284.dp)
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        this.alpha = alpha
                        this.rotationX = rotationX
                        this.translationX = translationX
                        this.translationY = translationY
                        this.cameraDistance = 12 * density
                        clip = false
                    }
                    .zIndex(1f - abs(pageOffset))
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { onSnackClick() }
                    .background(LightBackground, shape = RoundedCornerShape(36.dp))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = snacks[page]),
                        contentDescription = "Snack",
                        modifier = Modifier.size(width = 150.dp, height = 180.dp)
                    )
                }
            }
        }
    }
}