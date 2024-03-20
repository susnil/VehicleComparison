package pl.mobilespot.vehiclecomparison.presentation.collection

data class CollectionState(
    val collectionUiState: CollectionUiState = CollectionUiState.Loading,
    val selected: List<Int> = mutableListOf<Int>()
)