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
    override val MGLT: Long,
    override val maxAtmospheringSpeed: Long?,

    ) : ComparableAttributes(
    films,
    pilots,
    cargoCapacity,
    costInCredits,
    crew,
    passengers,
    length,
    hyperdriveRating,
    MGLT,
    maxAtmospheringSpeed
) {
    companion object {
        val raw = Metrics(0, 0, 0L, 0L, 0L, 0L, 0.0F, 0.0F, 0, null)
    }
}