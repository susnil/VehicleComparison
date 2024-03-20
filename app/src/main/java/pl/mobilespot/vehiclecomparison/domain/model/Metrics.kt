package pl.mobilespot.vehiclecomparison.domain.model

data class Metrics(
    override val films: Int,
    override val pilots: Int,
) : ComparableAttributes(films, pilots)