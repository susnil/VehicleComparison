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

    private val _filterForm: MutableStateFlow<FilterForm> = MutableStateFlow(FilterForm.raw)
    val filterForm: StateFlow<FilterForm> = _filterForm.asStateFlow()

    private val allStarships = getStarship().cachedIn(viewModelScope)

    val starships = allStarships.combine(filterForm) { pagingData, form ->
        if (form.isEmpty()) {
            pagingData
        } else {
            pagingData
                .filter {
                    if (form.name.isEmpty()) true else {
                        it.name.startsWith(form.name, ignoreCase = true)
                    }
                }.filter {
                    if (form.manufacturer.isEmpty()) true else {
                        it.manufacturer.any { it.contains(form.manufacturer, ignoreCase = true) }
                    }
                }
        }
    }.cachedIn(viewModelScope)

    fun searchName(name: String, manufacturer: String) {
        _filterForm.update { FilterForm(name, manufacturer) }
        Timber.d("Search form: $name $manufacturer")
    }

    fun clearFilter() {
        _filterForm.update { FilterForm.raw }
        Timber.d("Disable filter")
    }

}