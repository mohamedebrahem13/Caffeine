package com.caffeine.ui.screens.coffee_order.composable

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import kotlin.random.Random
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.Path

@Composable
fun LoadingLine() {
    val infiniteTransition = rememberInfiniteTransition(label = "LinePingPongAnimation")

    val progress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "lineProgress"
    )

    val randomPath = remember { Path() }

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp)
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val waveCount = 10
        val waveAmplitude = canvasHeight / 2.5f

        if (randomPath.isEmpty) {
            randomPath.apply {
                moveTo(0f, canvasHeight / 2)
                val segmentWidth = canvasWidth / waveCount
                for (i in 1..waveCount) {
                    val endX = i * segmentWidth
                    val controlX = endX - (segmentWidth / 2)
                    val direction = if (i % 2 != 0) -1 else 1
                    val randomAmplitudeOffset = waveAmplitude * (Random.nextFloat() * 0.5f + 0.5f)
                    val controlY = (canvasHeight / 2) + (randomAmplitudeOffset * direction)
                    randomPath.quadraticTo(controlX, controlY, endX, canvasHeight / 2)
                }
            }
        }

        val pathMeasure = PathMeasure()
        pathMeasure.setPath(randomPath, false)
        val segmentPath = Path()

        pathMeasure.getSegment(
            startDistance = 0f,
            stopDistance = pathMeasure.length * progress,
            destination = segmentPath,
            startWithMoveTo = true
        )

        drawPath(
            path = segmentPath,
            color = Color(0xCC000000),
            style = Stroke(width = 5f, cap = StrokeCap.Round)
        )
    }
}