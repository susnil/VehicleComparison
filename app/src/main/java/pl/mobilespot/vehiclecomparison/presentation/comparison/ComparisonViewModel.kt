package pl.mobilespot.vehiclecomparison.presentation.comparison

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.mobilespot.vehiclecomparison.domain.model.Starship
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ComparisonViewModel @Inject constructor() : ViewModel() {
    private val selected: MutableSet<Starship> = mutableSetOf()

    fun select(starship: Starship) {
        Timber.d("Selected ${starship.name}. ")
        if (hasSelected(starship)) {
            selected.remove(starship)
        } else
            selected.add(starship)
    }

    fun hasSelected(starship: Starship) = selected.contains(starship)
}