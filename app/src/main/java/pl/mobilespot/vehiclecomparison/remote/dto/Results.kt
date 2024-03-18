package pl.mobilespot.vehiclecomparison.remote.dto

data class Results<T>(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<T>)
