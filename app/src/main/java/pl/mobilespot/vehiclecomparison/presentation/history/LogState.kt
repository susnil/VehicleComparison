package pl.mobilespot.vehiclecomparison.presentation.history

import pl.mobilespot.vehiclecomparison.domain.model.Log

data class LogState(
    val logs: List<Log> = emptyList()
)