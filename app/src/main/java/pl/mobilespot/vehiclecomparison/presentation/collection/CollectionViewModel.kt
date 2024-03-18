package pl.mobilespot.vehiclecomparison.presentation.collection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.mobilespot.vehiclecomparison.core.FakeData
import pl.mobilespot.vehiclecomparison.domain.model.Starship
import timber.log.Timber

class CollectionViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CollectionState())
    val uiState: StateFlow<CollectionState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            delay(3000)
            _uiState.update {
                CollectionState(
                    collectionUiState = CollectionUiState.Success(
                        FakeData.starship
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
    data class Success(val starships: Starship) : CollectionUiState()
    object Error : CollectionUiState()
    object Loading : CollectionUiState()
}
