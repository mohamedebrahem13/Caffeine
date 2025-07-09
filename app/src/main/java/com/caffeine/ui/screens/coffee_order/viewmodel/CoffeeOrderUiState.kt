package com.caffeine.ui.screens.coffee_order.viewmodel

data class CoffeeOrderUiState(
    val selectedSize: CupSize = CupSize.MEDIUM,
    val selectedStrength: CoffeeStrength = CoffeeStrength.MEDIUM,
    val animatedStrengths: Set<CoffeeStrength> = emptySet(),
    val isProcessing: Boolean = false
)
enum class CupSize(val scale: Float, val volumeText: String, val displayName: String) {
    SMALL(0.65f, "150 ML", "S"),
    MEDIUM(0.8f, "200 ML", "M"),
    LARGE(1f, "400 ML", "L")
}

enum class CoffeeStrength(val displayName: String, val targetAlpha: Float) {
    LOW("Low", 0.4f),
    MEDIUM("Medium", 0.6f),
    HIGH("High", 1.0f)
}