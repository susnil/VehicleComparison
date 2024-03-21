package pl.mobilespot.vehiclecomparison.presentation.comparison

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.mobilespot.vehiclecomparison.core.Constants
import pl.mobilespot.vehiclecomparison.domain.model.MinMaxMetrics
import pl.mobilespot.vehiclecomparison.domain.model.Starship
import pl.mobilespot.vehiclecomparison.domain.usecase.CompareUseCase
import pl.mobilespot.vehiclecomparison.domain.usecase.UpsertLog
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ComparisonViewModel @Inject constructor(
    val compareUseCase: CompareUseCase,
    private val upsertLog: UpsertLog
) : ViewModel() {

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
        val hasSelected = hasSelected(starship)
        if (hasSelected) {
            update(starship, true)
        } else {
            update(starship, false)
        }
        Timber.d("Selected ${starship.name}. Selected elements: ${starshipsCount()}. ")
        return !hasSelected
    }

    private fun starshipsCount() = _uiState.value.starships.count()

    private fun update(starship: Starship, contains: Boolean) {
        _uiState.update {
            val starships = mutableSetOf<Starship>()
            starships.addAll(_uiState.value.starships)
            if (contains) {
                starships.remove(starship)
            } else starships.add(starship)
            _uiState.value.copy(starships = starships, metrics = compareUseCase(starships))
        }
    }

    fun openComparison(starships: Set<Starship>) {
        viewModelScope.launch {
            if (starships.count() >= Constants.MIN_ELEMENTS_TO_COMPARE) {
                upsertLog.invoke(starships)
            }
        }
    }
}