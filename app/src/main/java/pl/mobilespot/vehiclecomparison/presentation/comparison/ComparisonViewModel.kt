package pl.mobilespot.vehiclecomparison.presentation.comparison

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import pl.mobilespot.vehiclecomparison.domain.model.Metrics
import pl.mobilespot.vehiclecomparison.domain.model.MinMaxMetrics
import pl.mobilespot.vehiclecomparison.domain.model.Starship
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ComparisonViewModel @Inject constructor() : ViewModel() {

    private val _uiState =
        MutableStateFlow(
            ComparisonUiState(
                emptySet(),
                MinMaxMetrics.raw
            )
        )
    val uiState = _uiState.asStateFlow()

    fun select(starship: Starship): Boolean {
        Timber.d("Selected ${starship.name} ")
        val hasSelected = hasSelected(starship)
        if (hasSelected) {
            remove(starship)
        } else {
            add(starship)
        }
        Timber.d("Count: ${starshipsCount()}. ")
        return !hasSelected
    }

    private fun starshipsCount() = _uiState.value.starships.count()

    private val starships
        get() = _uiState.value.starships

    private fun add(starship: Starship) {
        _uiState.update {
            val starships = mutableSetOf<Starship>()
            starships.addAll(_uiState.value.starships)
            starships.add(starship)
            _uiState.value.copy(starships, compareAttributes(starships))
        }
    }

    private fun remove(starship: Starship) {
        _uiState.update {
            val starships = mutableSetOf<Starship>()
            starships.addAll(_uiState.value.starships)
            starships.remove(starship)
            _uiState.value.copy(starships, compareAttributes(starships))
        }
    }

    private fun compareAttributes(starships: Set<Starship>): MinMaxMetrics {
        return if (starships.count() >= 2) {
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

    fun hasSelected(starship: Starship) = _uiState.value.starships.contains(starship)
}