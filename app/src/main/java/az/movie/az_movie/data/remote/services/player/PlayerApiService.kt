package az.movie.az_movie.data.remote.services.player

import az.movie.az_movie.domain.model.playerDataModel.PlayerData
import az.movie.az_movie.util.endpoints.MovieApiEndpoints
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface PlayerApiService {

    @GET(MovieApiEndpoints.FILE)
    suspend fun getPlayer(
        @Path("id") id: Int ,
        @Path("season") season: Int ,
    ): Response<PlayerData>

}