package com.caffeine.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.caffeine.android.theme.TextDarkGray

@Composable
fun ButtonWithIcon(
    modifier: Modifier = Modifier,
    text: String,
    icon: Painter,
    onClick: () -> Unit
) {
    val shadowColor = Black.copy(alpha = 0.24f)
    val shadowRadius = 12.dp
    val shadowOffsetY = 6.dp
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = TextDarkGray),
        modifier = modifier.wrapContentSize()
            .drawBehind {
                val paint = Paint().asFrameworkPaint().apply {
                    isAntiAlias = true
                    color = shadowColor.toArgb()
                    setShadowLayer(
                        shadowRadius.toPx(),
                        0f,
                        shadowOffsetY.toPx(),
                        shadowColor.toArgb()
                    )
                }
                drawIntoCanvas {
                    it.nativeCanvas.drawRoundRect(
                        0f,
                        0f,
                        size.width,
                        size.height,
                        100.dp.toPx(),
                        100.dp.toPx(),
                        paint
                    )
                }
            },
        shape = RoundedCornerShape(100.dp),
        contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = text,
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(end = 8.dp)
            )

            Icon(
                painter = icon,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}