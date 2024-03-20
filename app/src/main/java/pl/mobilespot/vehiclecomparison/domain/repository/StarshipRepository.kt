package pl.mobilespot.vehiclecomparison.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import pl.mobilespot.vehiclecomparison.domain.model.Starship

interface StarshipRepository {
    fun getStarships(): Flow<PagingData<Starship>>
}