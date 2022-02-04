package az.movie.az_movie.data.repository.player

import az.movie.az_movie.model.moviesDataModel.ItemMovie
import az.movie.az_movie.model.moviesDataModel.MovieData
import az.movie.az_movie.model.playerDataModel.PlayerData
import az.movie.az_movie.model.trailerDataModel.TrailerData
import az.movie.az_movie.util.response_handler.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow



interface PlayerRepository {

    suspend fun getPlayerMovie(movieId: Int, episode: Int): Flow<Resource<PlayerData>>
    suspend fun getPlayerSeries(movieId: Int, episode: Int):Flow<Resource<PlayerData>>

}