package pl.mobilespot.vehiclecomparison.domain.model

data class MinMaxMetrics(val minMetrics: Metrics, val maxMetrics: Metrics) {
    companion object {
        val raw = MinMaxMetrics(
            minMetrics = Metrics(0, 0, 0L, 0L, 0L, 0L, 0.0F, 0.0F),
            maxMetrics = Metrics(0, 0, 0L, 0L, 0L, 0L, 0.0F, 0.0F)
        )
    }
}