package pl.mobilespot.vehiclecomparison.domain.usecase

import pl.mobilespot.vehiclecomparison.domain.model.Metrics
import pl.mobilespot.vehiclecomparison.domain.model.MinMaxMetrics
import pl.mobilespot.vehiclecomparison.domain.model.Starship
import timber.log.Timber
import javax.inject.Inject

private const val MIN_ELEMENTS_TO_COMPARE = 2

class CompareUseCase @Inject constructor() {
    operator fun invoke(starships: MutableSet<Starship>): MinMaxMetrics {
        return if (starships.count() >= MIN_ELEMENTS_TO_COMPARE) {
            MinMaxMetrics(
                minMetrics = Metrics(
                    starships.minOfOrNull { it.films } ?: 0,
                    starships.minOfOrNull { it.pilots } ?: 0,
                    starships.minOfOrNull { it.cargoCapacity } ?: 0L,
                    starships.minOfOrNull { it.costInCredits } ?: 0L,
                    starships.minOfOrNull { it.crew } ?: 0L,
                    starships.minOfOrNull { it.passengers } ?: 0L,
                    starships.minOfOrNull { it.length } ?: 0.0F,
                    starships.minOfOrNull { it.hyperdriveRating } ?: 0.0F,
                ),
                maxMetrics = Metrics(
                    starships.maxOfOrNull { it.films } ?: 0,
                    starships.maxOfOrNull { it.pilots } ?: 0,
                    starships.maxOfOrNull { it.cargoCapacity } ?: 0L,
                    starships.maxOfOrNull { it.costInCredits } ?: 0L,
                    starships.maxOfOrNull { it.crew } ?: 0L,
                    starships.maxOfOrNull { it.passengers } ?: 0L,
                    starships.maxOfOrNull { it.length } ?: 0.0F,
                    starships.maxOfOrNull { it.hyperdriveRating } ?: 0.0F,
                ),
            ).also { Timber.d("CompareAttributes: $it") }
        } else MinMaxMetrics.raw
    }
}