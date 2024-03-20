package pl.mobilespot.vehiclecomparison.domain.usecase

import org.junit.Assert.assertEquals
import org.junit.Test
import pl.mobilespot.vehiclecomparison.core.FakeData
import pl.mobilespot.vehiclecomparison.domain.model.Metrics
import pl.mobilespot.vehiclecomparison.domain.model.MinMaxMetrics
import pl.mobilespot.vehiclecomparison.domain.model.Starship

class CompareUseCaseTest {

    @Test
    fun `invoke with less than minimum elements should return MinMaxMetrics raw`() {
        // given
        val starships = mutableSetOf<Starship>()
        val compareUseCase = CompareUseCase()

        // when
        val result = compareUseCase(starships)

        // then
        assertEquals(MinMaxMetrics.raw, result)
    }

    @Test
    fun `invoke with minimum elements should return correct MinMaxMetrics`() {
        // given
        val starship1 = FakeData.starship1
        val starship2 = FakeData.starship2
        val starships = mutableSetOf(starship1, starship2)
        val compareUseCase = CompareUseCase()

        // when
        val result = compareUseCase(starships)

        // then
        val expectedMinMetrics = Metrics(
            films = 3,
            pilots = 2,
            cargoCapacity = 10000L,
            costInCredits = 2000000,
            crew = 10L,
            passengers = 25,
            length = 38.4F,
            hyperdriveRating = 2.0F,
            MGLT = 30L,
            maxAtmospheringSpeed = 32
        )
        val expectedMaxMetrics = Metrics(
            films = 5,
            pilots = 4,
            cargoCapacity = 100000L,
            costInCredits = 20000000L,
            crew = 101L,
            passengers = 250L,
            length = 68.4F,
            hyperdriveRating = 4.0F,
            MGLT = 31L,
            maxAtmospheringSpeed = 37
        )
        assertEquals(expectedMinMetrics, result.minMetrics)
        assertEquals(expectedMaxMetrics, result.maxMetrics)
    }

    @Test
    fun `invoke with 3 elements should return correct MinMaxMetrics`() {
        // given
        val starship1 = FakeData.starship1
        val starship2 = FakeData.starship2
        val starship3 = FakeData.starship3
        val starships = mutableSetOf(starship1, starship2, starship3)
        val compareUseCase = CompareUseCase()

        // when
        val result = compareUseCase(starships)

        // then
        val expectedMinMetrics = Metrics(
            films = 1,
            pilots = 2,
            cargoCapacity = 1005,
            costInCredits = 2000000,
            crew = 4L,
            passengers = 25,
            length = 38.4F,
            hyperdriveRating = 2.0F,
            MGLT = 29L,
            maxAtmospheringSpeed = 32
        )
        val expectedMaxMetrics = Metrics(
            films = 5,
            pilots = 6,
            cargoCapacity = 100000L,
            costInCredits = 35000000L,
            crew = 101L,
            passengers = 250L,
            length = 68.4F,
            hyperdriveRating = 4.0F,
            MGLT = 31L,
            maxAtmospheringSpeed = 37
        )
        assertEquals(expectedMinMetrics, result.minMetrics)
        assertEquals(expectedMaxMetrics, result.maxMetrics)
    }
}