package com.caffeine.ui.screens.coffee_order.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.caffeine.ui.screens.coffee_order.SizeSelector
import com.caffeine.ui.screens.coffee_order.StrengthSelector
import com.caffeine.ui.screens.coffee_order.viewmodel.CoffeeStrength
import com.caffeine.ui.screens.coffee_order.viewmodel.CupSize

@Composable
fun SelectorsSection(
    selectedSize: CupSize,
    selectedStrength: CoffeeStrength,
    onSizeSelected: (CupSize) -> Unit,
    onStrengthSelected: (CoffeeStrength) -> Unit,
    modifier: Modifier = Modifier
) {


    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SizeSelector(
            modifier = Modifier.padding(bottom = 16.dp),
            selectedSize = selectedSize,
            onSizeSelected = onSizeSelected
        )

        StrengthSelector(
            selectedStrength = selectedStrength,
            onStrengthSelected = onStrengthSelected
        )

        Spacer(modifier = Modifier.height(80.dp))

    }
}