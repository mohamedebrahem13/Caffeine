package com.caffeine.di

import com.caffeine.ui.screens.coffee_order.viewmodel.CoffeeOrderViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val coffeeModule = module {
    viewModel  { CoffeeOrderViewModel() }
}