package pl.mobilespot.vehiclecomparison.presentation.collection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.mobilespot.vehiclecomparison.core.FakeData
import pl.mobilespot.vehiclecomparison.domain.model.Starship
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CollectionViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(CollectionState())
    val uiState: StateFlow<CollectionState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            delay(100)
            _uiState.update {
                CollectionState(
                    collectionUiState = CollectionUiState.Success(
                        listOf(
                            FakeData.starship,
                            FakeData.starship.copy("Happy car", "Royce Rolls")
                        )
                    )
                )
            }
            Timber.d("Done")
        }

    }
}

data class CollectionState(
    val collectionUiState: CollectionUiState = CollectionUiState.Loading,
    val selected: List<Int> = mutableListOf<Int>()
)

sealed class CollectionUiState {
    data class Success(val starships: List<Starship>) : CollectionUiState()
    object Error : CollectionUiState()
    object Loading : CollectionUiState()
}
