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
    val cargoCapacity: Long,
    val costInCredits: Long,
    val crew: Long,
    val passengers: Long,
    val length: Float,
    val hyperdriveRating: Float,
    val created: LocalDateTime,
    val edited: LocalDateTime,
    val maxAtmospheringSpeed: Long?,
    val consumables: Period?,
    val MGLT: Long
) : ComparableAttributes(films, pilots)