package az.movie.az_movie.data.remote.datasources.player

import az.movie.az_movie.data.remote.services.player.PlayerApiService
import javax.inject.Inject

class PlayerDataSource @Inject constructor(private val api: PlayerApiService) {

    suspend fun getPlayer(movieId: Int, season: Int) = api.getPlayer(movieId, season)

}