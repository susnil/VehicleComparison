package pl.mobilespot.vehiclecomparison.core

import pl.mobilespot.vehiclecomparison.domain.model.Starship
import java.time.LocalDateTime
import java.time.Period

object FakeData {
    val starship1 = Starship(
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
    val starship2 = Starship(
        "Second Star",
        "Battle Station",
        "Mobile",
        listOf("As", "Sienar Fleet Systems"),
        5,
        2,
        10000,
        20000000,
        101,
        25,
        68.4F,
        2.0F,
        LocalDateTime.now().minusDays(2),
        LocalDateTime.now().minusDays(1),
        32,
        Period.ofYears(3),
        30
    )
    val starship3 = Starship(
        "Third Star",
        "Big Station",
        "Plane",
        listOf("As"),
        1,
        6,
        1005,
        35000000,
        4,
        28,
        58.3F,
        3.0F,
        LocalDateTime.now().minusDays(4),
        LocalDateTime.now().minusDays(3),
        34,
        Period.ofMonths(2),
        29
    )
}