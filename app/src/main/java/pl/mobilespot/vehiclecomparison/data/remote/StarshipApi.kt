package pl.mobilespot.vehiclecomparison.data.remote

import pl.mobilespot.vehiclecomparison.data.remote.dto.Results
import pl.mobilespot.vehiclecomparison.data.remote.dto.StarshipDto
import retrofit2.http.GET
import retrofit2.http.Query

interface StarshipApi {

    @GET("starships")
    suspend fun getStarships(
        @Query("page") page: Int = 1,
    ): Results<StarshipDto>
}