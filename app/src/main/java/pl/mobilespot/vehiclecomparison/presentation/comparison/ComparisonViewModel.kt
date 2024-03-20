package pl.mobilespot.vehiclecomparison.presentation.comparison

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import pl.mobilespot.vehiclecomparison.domain.model.Starship
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ComparisonViewModel @Inject constructor() : ViewModel() {

    init {
        Timber.d("Init shared viewmodel")
    }

    private val _uiState = MutableStateFlow(ComparisonUiState(emptySet()))
    val uiState = _uiState.asStateFlow()

    fun select(starship: Starship) {
        Timber.d("Selected ${starship.name} ")
        if (hasSelected(starship)) {
            remove(starship)
        } else {
            add(starship)
        }
        Timber.d("Count: ${_uiState.value.set.count()}. ")
    }

    private fun remove(starship: Starship) {
        _uiState.update {
            val set = mutableSetOf<Starship>()
            set.addAll(_uiState.value.set)
            set.remove(starship)
            ComparisonUiState(set)
        }
    }

    private fun add(starship: Starship) {
        _uiState.update {
            val set = mutableSetOf<Starship>()
            set.addAll(_uiState.value.set)
            set.add(starship)
            ComparisonUiState(set)
        }
    }

    fun hasSelected(starship: Starship) = _uiState.value.set.contains(starship)
}