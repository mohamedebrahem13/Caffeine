package com.caffeine.ui.screens.coffee_ready.composable

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.caffeine.R
import com.caffeine.android.theme.SwitchTrackOffColor
import com.caffeine.android.theme.SwitchTrackOnColor
import com.caffeine.android.theme.TextDarkGray

@Composable
fun CoffeeSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {

    val trackColor by animateColorAsState(
        targetValue = if (checked) SwitchTrackOnColor else SwitchTrackOffColor,
        animationSpec = tween(900),
        label = "trackColor"
    )

    val horizontalBias by animateFloatAsState(
        targetValue = if (checked) -1f else 1f,
        animationSpec = tween(900),
        label = "thumbBias"
    )

    val thumbAlignment = BiasAlignment(horizontalBias = horizontalBias, verticalBias = 0f)


    val rotation by animateFloatAsState(
        targetValue = if (checked) 0f else 120f,
        animationSpec = tween(900),
        label = "rotation"
    )

    Box(
        modifier = modifier
            .height(40.dp)
            .width(78.dp)
            .clip(CircleShape)
            .background(trackColor)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = { onCheckedChange(!checked) }
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Text(
                stringResource(R.string.off),
                color = TextDarkGray.copy(alpha = 0.6f),
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp,
                fontFamily = FontFamily(
                    Font(R.font.urbanist_bold, FontWeight.Bold)
                ),
            )
            Text(
                text = stringResource(R.string.on),
                color = TextDarkGray.copy(alpha = 0.6f),
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp,
                fontFamily = FontFamily(
                    Font(R.font.urbanist_bold, FontWeight.Bold)
                ),
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = thumbAlignment
        ) {
            Image(
                painter = painterResource(id = R.drawable.coffee_switch),
                contentDescription = stringResource(R.string.switch_coffee),
                modifier = Modifier
                    .graphicsLayer { rotationZ = rotation }
                    .size(40.dp)
                    .clip(CircleShape)
            )
        }
    }
}