package com.caffeine.ui.screens.coffee_order.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.caffeine.R
import com.caffeine.android.theme.LightBackground
import com.caffeine.android.theme.TextDarkGray

@Composable
fun Header(
    onClickBack: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 65.dp)
            .height(48.dp)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(LightBackground)
                .clickable {
                    println("Back clicked!")
                    onClickBack()
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.arrow_left_04),
                contentDescription = "Back Button!",
                tint = TextDarkGray.copy(alpha = 0.87f),
                modifier = Modifier.size(24.dp)
            )
        }
        Text(
            text = "Macchiato",
            style = MaterialTheme.typography.titleSmall,
            color = TextDarkGray.copy(alpha = 0.87f)
        )
    }
}