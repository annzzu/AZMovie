package az.movie.az_movie.data.repository.movie

import az.movie.az_movie.data.remote.datasources.movie.MovieDataSource
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val dataSource: MovieDataSource) :
    MovieRepository {
//
//    override suspend fun getAirports(): Flow<Resource<AirportResponse>> {
//        return flow {
//            emit(Resource.Loading())
//            emit(handleResponse { dataSource.getMovies() })
//        }.flowOn(Dispatchers.IO)
//    }

}