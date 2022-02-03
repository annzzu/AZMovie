package az.movie.az_movie.data.repository.trailer

import az.movie.az_movie.data.remote.datasources.trailer.TrailerDataSource
import az.movie.az_movie.model.trailerDataModel.TrailerData
import az.movie.az_movie.util.response_handler.Resource
import kotlinx.coroutines.flow.Flow


interface TrailerRepository {

    suspend fun getTrailersData(): Flow<Resource<TrailerData>>

}