package pl.mobilespot.vehiclecomparison.domain.model

data class MinMaxMetrics(val minMetrics: Metrics, val maxMetrics: Metrics) {
    companion object {
        val raw = MinMaxMetrics(
            minMetrics = Metrics.raw,
            maxMetrics = Metrics.raw
        )
    }
}