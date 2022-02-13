package az.movie.az_movie.data.repository.player

import az.movie.az_movie.model.playerDataModel.PlayerData
import az.movie.az_movie.domain.response_handler.Resource
import kotlinx.coroutines.flow.Flow


interface PlayerRepository {

    suspend fun getPlayerMovie(movieId: Int, season: Int): Flow<Resource<PlayerData>>

}