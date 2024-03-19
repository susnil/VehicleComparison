package pl.mobilespot.vehiclecomparison.presentation.collection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.mobilespot.vehiclecomparison.core.di.AppModule.provideApiInstance
import pl.mobilespot.vehiclecomparison.data.mapper.StarShipMapper
import pl.mobilespot.vehiclecomparison.domain.model.Starship
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CollectionViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(CollectionState())
    val uiState: StateFlow<CollectionState> = _uiState.asStateFlow()

    @Inject
    lateinit var starShipMapper: StarShipMapper

    init {
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {

                val collection = mutableListOf<Starship>()
                var page = 1
                var isWorking = true
                do {
                    val result = provideApiInstance().getStarships(page)
                    Timber.d("Input dto: $result")
                    val newStarships = result.results.map { dto -> starShipMapper.fromDto(dto) }
                    collection.addAll(newStarships)
                    if (result.next == null) {
                        isWorking = false
                    }

                    _uiState.update {
                        CollectionState(
                            collectionUiState =
                            if (isWorking) {
                                CollectionUiState.PartiallySuccess(
                                    collection
                                )
                            } else CollectionUiState.FullySuccess(
                                collection
                            )
                        )
                    }
                    page++
                } while (isWorking)
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
