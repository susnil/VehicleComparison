package pl.mobilespot.vehiclecomparison.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import pl.mobilespot.vehiclecomparison.data.mapper.StarShipMapper
import pl.mobilespot.vehiclecomparison.domain.model.Starship
import timber.log.Timber
import javax.inject.Inject

class StarshipPagingSource @Inject constructor(private val starshipApi: StarshipApi, private val starShipMapper: StarShipMapper) : PagingSource<Int, Starship>() {

    override fun getRefreshKey(state: PagingState<Int, Starship>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Starship> {
        val page = params.key ?: 1
        return try {
            val response = starshipApi.getStarships(page = page)
            val articles = response.results.map { starShipMapper.fromDto(it) }
            Timber.d("Fetched page: $page, next: ${response.next}")
            LoadResult.Page(
                data = articles,
                nextKey = if (response.next == null) null else page + 1,
                prevKey = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }
}