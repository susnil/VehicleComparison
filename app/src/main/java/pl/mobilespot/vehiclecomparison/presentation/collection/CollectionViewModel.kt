package pl.mobilespot.vehiclecomparison.presentation.collection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.filter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import pl.mobilespot.vehiclecomparison.domain.usecase.GetStarship
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CollectionViewModel @Inject constructor(getStarship: GetStarship) : ViewModel() {

    private val _wordFlow: MutableStateFlow<String> = MutableStateFlow("")
    val wordFlow: StateFlow<String> = _wordFlow.asStateFlow()

    private val allStarships = getStarship().cachedIn(viewModelScope)

    val starships = allStarships.combine(wordFlow) { pagingData, searchName ->
        if (searchName.isEmpty()) {
            pagingData
        } else {
            pagingData
                .filter { it.model.startsWith(searchName, ignoreCase = true) }
        }
    }.cachedIn(viewModelScope)

    fun searchName(text: String) {
        _wordFlow.update { text }
        Timber.d("Search name: $text")
    }

    fun clearFilter() {
        _wordFlow.update { "" }
        Timber.d("Disable filter")
    }

}

