package pl.mobilespot.vehiclecomparison.remote.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Starship(
    val name: String,
    val model: String,
    @SerializedName("starship_class")
    val starshipClass: String,
    val manufacturer: String,
    @SerializedName("cost_in_credits")
    val costInCredits: String,
    val length: String,
    val crew: String,
    val passengers: String,
    @SerializedName("max_atmosphering_speed")
    val maxAtmospheringSpeed: String,
    @SerializedName("hyperdrive_rating")
    val hyperdriveRating: String,
    val MGLT: String,
    @SerializedName("cargo_capacity")
    val cargoCapacity: String,
    val consumables: String,
    val films: List<String>,
    val pilots: List<String>,
    val url: String,
    val created: String,
    val edited: String
) : Parcelable