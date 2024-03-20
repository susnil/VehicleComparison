package pl.mobilespot.vehiclecomparison.domain.model

abstract class ComparableAttributes(
    open val films: Int,
    open val pilots: Int,
    open val cargoCapacity: Long,
    open val costInCredits: Long,
    open val crew: Long,
    open val passengers: Long,
    open val length: Float,
    open val hyperdriveRating: Float,
    open val MGLT: Long,
    open val maxAtmospheringSpeed: Long?,
)