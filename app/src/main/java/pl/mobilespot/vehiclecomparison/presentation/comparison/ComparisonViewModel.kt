package pl.mobilespot.vehiclecomparison.presentation.comparison

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import pl.mobilespot.vehiclecomparison.domain.model.MinMaxMetrics
import pl.mobilespot.vehiclecomparison.domain.model.Starship
import pl.mobilespot.vehiclecomparison.domain.usecase.CompareUseCase
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ComparisonViewModel @Inject constructor(val compareUseCase: CompareUseCase) : ViewModel() {

    private val _uiState =
        MutableStateFlow(
            ComparisonUiState(
                emptySet(),
                MinMaxMetrics.raw
            )
        )
    val uiState = _uiState.asStateFlow()
    fun hasSelected(starship: Starship) = _uiState.value.starships.contains(starship)
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

    private fun add(starship: Starship) {
        _uiState.update {
            val starships = mutableSetOf<Starship>()
            starships.addAll(_uiState.value.starships)
            starships.add(starship)
            _uiState.value.copy(starships, compareUseCase(starships))
        }
    }

    private fun remove(starship: Starship) {
        _uiState.update {
            val starships = mutableSetOf<Starship>()
            starships.addAll(_uiState.value.starships)
            starships.remove(starship)
            _uiState.value.copy(starships, compareUseCase(starships))
        }
    }


}