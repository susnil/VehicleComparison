package pl.mobilespot.vehiclecomparison.presentation.comparison

import pl.mobilespot.vehiclecomparison.domain.model.MinMaxMetrics
import pl.mobilespot.vehiclecomparison.domain.model.Starship

data class ComparisonUiState(
    val starships: Set<Starship>,
    val metrics: MinMaxMetrics
)