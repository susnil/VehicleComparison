package pl.mobilespot.vehiclecomparison.remote

import pl.mobilespot.vehiclecomparison.remote.dto.Results
import pl.mobilespot.vehiclecomparison.remote.dto.Starship
import retrofit2.http.GET
import retrofit2.http.Query

interface StarshipApi {

    @GET("starships")
    suspend fun getStarships(@Query("page") page: Int = 1,
    ): Results<Starship>
}