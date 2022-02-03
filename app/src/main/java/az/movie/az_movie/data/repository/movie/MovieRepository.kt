package az.movie.az_movie.data.repository.movie

import az.movie.az_movie.model.moviesDataModel.MovieData
import az.movie.az_movie.util.response_handler.Resource
import kotlinx.coroutines.flow.Flow


interface MovieRepository {
    abstract suspend fun getMovies(pageNumber: Int ,is_movie: Boolean): Flow<Resource<MovieData>>

//    suspend fun getAirports(): Flow<Resource<AirportResponse>>

}