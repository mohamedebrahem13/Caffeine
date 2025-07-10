package com.caffeine.ui.screens.snackpicker

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.caffeine.R
import com.caffeine.android.theme.LightBackground
import com.caffeine.android.theme.TextDarkGray

@Composable
fun SnackPickerScreen(
    onExitClick: () -> Unit,
    onSnackClick: (Int) -> Unit
) {
    val snacks = listOf(
        R.drawable.chocolate,
        R.drawable.biscuit,
        R.drawable.cookies,
        R.drawable.croissant,
        R.drawable.cup_cake,
        R.drawable.bun,
        R.drawable.chocolate,
        R.drawable.biscuit,
        R.drawable.cookies,
        R.drawable.croissant,
        R.drawable.cup_cake,
        R.drawable.bun,
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.Start
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

        Text(
            text = "Take your snack!",
            style = MaterialTheme.typography.titleLarge,
            color = TextDarkGray.copy(alpha = 0.87f),
            modifier = Modifier.padding(top = 12.dp, bottom = 16.dp)
        )

        CurvedSnackScrollPager(
            snacks = snacks,
            onSnackClick = onSnackClick
        )
    }
}