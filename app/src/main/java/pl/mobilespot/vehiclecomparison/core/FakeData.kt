package pl.mobilespot.vehiclecomparison.core

import pl.mobilespot.vehiclecomparison.domain.model.Starship

object FakeData {
    val starship = Starship(
        "Death Star",
        "DS-1 Orbital Battle Station",
        "Deep Space Mobile Battlestation",
        listOf("Imperial Department of Military Research", "Sienar Fleet Systems"),
        3,
        4,

    )
}