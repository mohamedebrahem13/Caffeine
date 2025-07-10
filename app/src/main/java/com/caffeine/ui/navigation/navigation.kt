package com.caffeine.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.caffeine.R
import com.caffeine.ui.screens.CoffeePagerScreen
import com.caffeine.ui.screens.HomeIntroScreen
import com.caffeine.ui.screens.ThankYouScreen
import com.caffeine.ui.screens.coffee_order.CoffeeOrderScreen
import com.caffeine.ui.screens.coffee_ready.CoffeeReadyScreen
import com.caffeine.ui.screens.snackpicker.SnackPickerScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Pager : Screen("pager")
    object Order : Screen("order")
    object Ready : Screen("ready")
    object SnackPicker : Screen("snack_picker") // NEW
    object ThankYou : Screen("thank_you/{snackId}") {
        fun createRoute(snackId: Int) = "thank_you/$snackId"
    }
}


@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {

        composable(Screen.Home.route) {
            HomeIntroScreen(
                onBringCoffeeClick = { navController.navigate(Screen.Pager.route) }
            )
        }

        composable(Screen.Pager.route) {
            CoffeePagerScreen(
                onClick = { navController.navigate(Screen.Order.route) }
            )
        }

        composable(Screen.Order.route) {
            CoffeeOrderScreen(
                onClickBack = { navController.popBackStack() },
                onNavigateToProcessing = {
                    navController.navigate(Screen.Ready.route)
                }
            )
        }

        composable(Screen.Ready.route) {
            CoffeeReadyScreen(
                onBackClicked = { navController.popBackStack() },
                onContinueClicked = {
                    navController.navigate(Screen.SnackPicker.route)
                }
            )
        }

        composable(Screen.SnackPicker.route) {
            SnackPickerScreen(
                onExitClick = { navController.popBackStack() },
                onSnackClick = { page ->
                    navController.navigate(Screen.ThankYou.createRoute(page))
                }
            )
        }

        composable(Screen.ThankYou.route) { backStackEntry ->
            val snackId = backStackEntry.arguments?.getString("snackId")?.toIntOrNull() ?: R.drawable.cup_cake

            ThankYouScreen(
                selectedPageIndex = snackId,
                onExitClick = {
                    navController.popBackStack(Screen.Home.route, inclusive = false)
                },
                onThankYouClick = {
                    navController.popBackStack(Screen.Home.route, inclusive = false)
                }
            )
        }
    }
}
