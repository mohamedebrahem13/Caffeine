package com.caffeine.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.caffeine.R
import com.caffeine.android.theme.CoffeeBrown
import com.caffeine.android.theme.LightBackground
import com.caffeine.android.theme.TextDarkGray
import com.caffeine.ui.composable.ButtonWithIcon

@Composable
fun ThankYouScreen(
    selectedImageResId: Int,
    onExitClick: () -> Unit,
    onThankYouClick: () -> Unit
) {
    var showButton by remember { mutableStateOf(false) }
    var showCancelButton by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        showButton = true
        showCancelButton = true
    }

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        AnimatedVisibility(
            visible = showCancelButton,
            enter = slideInVertically(
                initialOffsetY = { -it },
                animationSpec = tween(durationMillis = 500)
            )
        ) {
            Box(
                modifier = Modifier
                    .padding(start = 16.dp, top = 56.dp)
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(LightBackground)
                    .clickable { onExitClick() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.cancel_01),
                    contentDescription = "Cancel Button!",
                    tint = TextDarkGray.copy(alpha = 0.87f),
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier.padding(bottom = 24.dp)) {
                Icon(
                    painter = painterResource(R.drawable.coffee_beans),
                    contentDescription = null,
                    tint = CoffeeBrown,
                    modifier = Modifier.padding(end = 16.dp)
                )
                Text(
                    text = stringResource(R.string.more_espresso_less_depresso),
                    color = CoffeeBrown,
                    style = MaterialTheme.typography.titleMedium
                )
                Icon(
                    painter = painterResource(R.drawable.coffee_beans),
                    contentDescription = null,
                    tint = CoffeeBrown,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

            Image(
                painter = painterResource(selectedImageResId),
                contentDescription = stringResource(R.string.snack),
                modifier = Modifier.size(300.dp)
            )

            Row(
                modifier = Modifier.padding(top = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.bon_app_tit),
                    color = TextDarkGray.copy(alpha = 0.8f),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Icon(
                    painter = painterResource(R.drawable.magic_wand_01),
                    contentDescription = stringResource(R.string.magic_wand),
                    tint = TextDarkGray.copy(alpha = 0.8f),
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            AnimatedVisibility(
                visible = showButton,
                enter = slideInVertically(
                    initialOffsetY = { it + 500 },
                    animationSpec = tween(durationMillis = 700)
                )
            ) {
                ButtonWithIcon(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 30.dp),
                    onClick = onThankYouClick,
                    text = stringResource(R.string.thank_youuu),
                    icon = painterResource(R.drawable.arrow_right_04)
                )
            }
        }
    }
}