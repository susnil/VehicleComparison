package pl.mobilespot.vehiclecomparison.domain.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime
import java.time.Period

data class Starship(
    val name: String,
    val model: String,
    @SerializedName("starship_class")
    val starshipClass: String,
    val manufacturer: List<String>,
    override val films: Int,
    override val pilots: Int,
    override val cargoCapacity: Long,
    override val costInCredits: Long,
    override val crew: Long,
    override val passengers: Long,
    override val length: Float,
    override val hyperdriveRating: Float,
    val created: LocalDateTime,
    val edited: LocalDateTime,
    override val maxAtmospheringSpeed: Long?,
    val consumables: Period?,
    override val MGLT: Long
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
)