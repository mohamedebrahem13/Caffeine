package com.caffeine.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
    Column (
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize().padding(top = 56.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(start = 16.dp)
                .size(48.dp)
                .clip(CircleShape)
                .background(LightBackground)
                .clickable { onExitClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.cancel_01),
                contentDescription =  "Cancel Button!",
                tint = TextDarkGray.copy(alpha = 0.87f),
                modifier = Modifier.size(24.dp)
            )
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

            ButtonWithIcon(
                modifier = Modifier.padding(bottom = 30.dp),
                onClick = onThankYouClick,
                text = stringResource(R.string.thank_youuu),
                icon = painterResource(R.drawable.arrow_right_04)
            )
        }
    }
}