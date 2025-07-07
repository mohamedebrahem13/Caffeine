package com.caffeine

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.caffeine.android.theme.CaffeineTheme
import com.caffeine.ui.screens.CoffeePagerScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CaffeineTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CoffeePagerScreen(
                    ){
                        selectedDrink ->
                        Log.d("Coffee", "User selected: ${selectedDrink.name}")

                    }
                }
            }
        }
    }
}