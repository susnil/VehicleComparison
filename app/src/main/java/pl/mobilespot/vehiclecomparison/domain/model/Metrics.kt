package pl.mobilespot.vehiclecomparison.domain.model

data class Metrics(
    override val films: Int,
    override val pilots: Int,
    override val cargoCapacity: Long,
    override val costInCredits: Long,
    override val crew: Long,
    override val passengers: Long,
    override val length: Float,
    override val hyperdriveRating: Float,
) : ComparableAttributes(
    films,
    pilots,
    cargoCapacity,
    costInCredits,
    crew,
    passengers,
    length,
    hyperdriveRating
)