package pl.mobilespot.vehiclecomparison.presentation.collection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.mobilespot.vehiclecomparison.core.FakeData
import pl.mobilespot.vehiclecomparison.data.mapper.StarShipMapper
import pl.mobilespot.vehiclecomparison.domain.model.Starship
import javax.inject.Inject

@HiltViewModel
class CollectionViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(CollectionState())
    val uiState: StateFlow<CollectionState> = _uiState.asStateFlow()

    @Inject
    lateinit var starShipMapper: StarShipMapper

    init {
        viewModelScope.launch {
            _uiState.update {
                CollectionState(
                    collectionUiState = CollectionUiState.FullySuccess(
                        listOf(
                            FakeData.starship1,
                            FakeData.starship2,
                            FakeData.starship3
                        )
                    )
                )
            }
        }
    }

}

data class CollectionState(
    val collectionUiState: CollectionUiState = CollectionUiState.Loading,
    val selected: List<Int> = mutableListOf<Int>()
)

sealed class CollectionUiState {
    abstract class Success(open val starships: List<Starship>) : CollectionUiState()
    data class PartiallySuccess(override val starships: List<Starship>) : Success(starships)
    data class FullySuccess(override val starships: List<Starship>) : Success(starships)
    object Error : CollectionUiState()
    object Loading : CollectionUiState()
}