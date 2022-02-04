package az.movie.az_movie.data.repository.player

import android.util.Log
import az.movie.az_movie.data.remote.datasources.movie.MovieDataSource
import az.movie.az_movie.data.remote.datasources.player.PlayerDataSource
import az.movie.az_movie.model.moviesDataModel.MovieData
import az.movie.az_movie.model.playerDataModel.PlayerData
import az.movie.az_movie.model.trailerDataModel.TrailerData
import az.movie.az_movie.util.response_handler.Resource
import az.movie.az_movie.util.response_handler.handleResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PlayerRepositoryImpl @Inject constructor(private val dataSource: PlayerDataSource) :
    PlayerRepository {

    override suspend fun getPlayerMovie(movieId: Int , season: Int): Flow<Resource<PlayerData>> {
        return flow {
            emit(Resource.Loading())
            emit(handleResponse { dataSource.getPlayer(movieId , season) })
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getPlayerSeries(movieId: Int , season: Int): Flow<Resource<PlayerData>> {
        return flow {
            emit(Resource.Loading())
            emit(handleResponse { dataSource.getPlayer(movieId , season) })
        }.flowOn(Dispatchers.IO)
    }


}