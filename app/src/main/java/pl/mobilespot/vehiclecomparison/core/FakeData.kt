package pl.mobilespot.vehiclecomparison.core

import pl.mobilespot.vehiclecomparison.domain.model.Starship
import java.time.LocalDateTime
import java.time.Period

object FakeData {
    val starship = Starship(
        "Death Star",
        "DS-1 Orbital Battle Station",
        "Deep Space Mobile Battlestation",
        listOf("Imperial Department of Military Research", "Sienar Fleet Systems"),
        3,
        4,
        100000,
        2000000,
        10,
        250,
        38.4F,
        4.0F,
        LocalDateTime.now().minusDays(1),
        LocalDateTime.now(),
        37,
        Period.ofYears(2),
        31
        )
}