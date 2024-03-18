package pl.mobilespot.vehiclecomparison.collection

import com.google.gson.annotations.SerializedName

data class Starship(
    val name: String,
    val model: String,
    @SerializedName("starship_class")
    val starshipClass: String,
    val manufacturer: List<String>)
