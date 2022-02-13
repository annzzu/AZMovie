package az.movie.az_movie.data.repository.trailer

import az.movie.az_movie.data.remote.datasources.trailer.TrailerDataSource
import az.movie.az_movie.model.trailerDataModel.TrailerData
import az.movie.az_movie.domain.response_handler.Resource
import az.movie.az_movie.domain.response_handler.handleResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TrailerRepositoryImpl @Inject constructor(private val dataSource: TrailerDataSource):TrailerRepository {

    override suspend fun getTrailersData(): Flow<Resource<TrailerData>> {
        return flow {
            emit(Resource.Loading())
            emit(handleResponse { dataSource.getTrailersData() })
        }.flowOn(Dispatchers.IO)
    }

}