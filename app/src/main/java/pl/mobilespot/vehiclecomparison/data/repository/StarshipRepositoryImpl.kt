package pl.mobilespot.vehiclecomparison.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import pl.mobilespot.vehiclecomparison.data.mapper.StarShipMapper
import pl.mobilespot.vehiclecomparison.data.remote.StarshipApi
import pl.mobilespot.vehiclecomparison.data.remote.StarshipPagingSource
import pl.mobilespot.vehiclecomparison.domain.model.Starship
import pl.mobilespot.vehiclecomparison.domain.repository.StarshipRepository
import javax.inject.Inject

class StarshipRepositoryImpl @Inject constructor(
    private val api: StarshipApi,
    private val mapper: StarShipMapper,
) : StarshipRepository {
    override fun getStarships(): Flow<PagingData<Starship>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                StarshipPagingSource(api, mapper)
            }
        ).flow
    }
}