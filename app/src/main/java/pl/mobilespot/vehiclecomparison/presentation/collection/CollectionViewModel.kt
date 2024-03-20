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

