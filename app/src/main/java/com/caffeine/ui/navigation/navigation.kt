package com.caffeine.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.caffeine.ui.screens.CoffeePagerScreen
import com.caffeine.ui.screens.HomeIntroScreen
import com.caffeine.ui.screens.coffee_order.CoffeeOrderScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Pager : Screen("pager")
    object Order : Screen("order")
}

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeIntroScreen(onBringCoffeeClick = {
                navController.navigate(Screen.Pager.route)
            })
        }
        composable(Screen.Pager.route) {
            CoffeePagerScreen(onClick = {
                navController.navigate(Screen.Order.route)
            })
        }
        composable(Screen.Order.route) {
            CoffeeOrderScreen(
                onClickBack = {
                    navController.popBackStack()
                },
                onNavigateToProcessing = {

                }
            )
        }
    }
}