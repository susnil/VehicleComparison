package pl.mobilespot.vehiclecomparison.presentation.collection

import pl.mobilespot.vehiclecomparison.domain.model.Starship

sealed class CollectionUiState {
    abstract class Success(open val starships: List<Starship>) : CollectionUiState()
    data class PartiallySuccess(override val starships: List<Starship>) : Success(starships)
    data class FullySuccess(override val starships: List<Starship>) : Success(starships)
    object Error : CollectionUiState()
    object Loading : CollectionUiState()
}