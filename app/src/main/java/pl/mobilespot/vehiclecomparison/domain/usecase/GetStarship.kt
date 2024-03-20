package pl.mobilespot.vehiclecomparison.domain.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import pl.mobilespot.vehiclecomparison.domain.model.Starship
import pl.mobilespot.vehiclecomparison.domain.repository.StarshipRepository
import javax.inject.Inject

class GetStarship @Inject constructor(
    private val starshipRepository: StarshipRepository
) {
    operator fun invoke(): Flow<PagingData<Starship>> {
        return starshipRepository.getStarships()
    }
}
