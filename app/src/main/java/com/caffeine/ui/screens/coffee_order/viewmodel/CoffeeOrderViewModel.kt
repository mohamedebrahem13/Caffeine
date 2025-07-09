package com.caffeine.ui.screens.coffee_order.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CoffeeOrderViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CoffeeOrderUiState())
    val uiState: StateFlow<CoffeeOrderUiState> = _uiState

    fun selectSize(newSize: CupSize) {
        _uiState.update { it.copy(selectedSize = newSize) }
    }

    fun selectStrength(newStrength: CoffeeStrength) {
        _uiState.update {
            val updatedSet = if (newStrength in it.animatedStrengths)
                it.animatedStrengths - newStrength
            else
                it.animatedStrengths + newStrength

            it.copy(
                selectedStrength = newStrength,
                animatedStrengths = updatedSet
            )
        }
    }

    fun startProcessing() {
        _uiState.update { it.copy(isProcessing = true) }
    }
}