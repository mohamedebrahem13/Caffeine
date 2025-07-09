package com.caffeine.ui.screens.coffee_order.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.caffeine.R
import com.caffeine.android.theme.TextDarkGray

@Composable
fun AlmostDone(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(top = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LoadingLine()
        Text(
            modifier= Modifier.padding(top = 37.dp),
            text = stringResource(R.string.almost_done),
            style =  MaterialTheme.typography.titleLarge,
            color = TextDarkGray.copy(alpha = 0.87f),
        )
        Text(
            text = stringResource(R.string.your_coffee_will_be_finish_in),
            color = TextDarkGray.copy(alpha = 0.6f),
           style =  MaterialTheme.typography.bodyLarge)
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                painter = painterResource(R.drawable.co),
                contentDescription = null,
                tint = Color.Unspecified
            )
            Icon(
                painter =painterResource(R.drawable.two_dots) ,
                modifier = Modifier.padding(horizontal = 12.dp),
                contentDescription=null,
                tint = Color.Unspecified
            )
            Icon(
                painter = painterResource(R.drawable.ff),
                contentDescription = null,
                tint = Color.Unspecified
            )
            Icon(
                painter =painterResource(R.drawable.two_dots) ,
                modifier = Modifier.padding(horizontal = 12.dp),
                contentDescription=null,
                tint = Color.Unspecified
            )
            Icon(
                painter = painterResource(R.drawable.ee),
                contentDescription = null,
                tint = Color.Unspecified
            )
        }
    }
}