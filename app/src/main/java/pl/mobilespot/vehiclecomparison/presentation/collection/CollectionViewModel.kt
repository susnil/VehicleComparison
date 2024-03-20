package pl.mobilespot.vehiclecomparison.presentation.collection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.mobilespot.vehiclecomparison.domain.usecase.GetStarship
import javax.inject.Inject

@HiltViewModel
class CollectionViewModel @Inject constructor(getStarship: GetStarship) : ViewModel() {

    val starships = getStarship(
    ).cachedIn(viewModelScope)
}

