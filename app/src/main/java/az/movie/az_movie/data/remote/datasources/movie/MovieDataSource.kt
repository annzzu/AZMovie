package az.movie.az_movie.data.remote.datasources.movie

import az.movie.az_movie.data.remote.services.movie.MovieApiService
import az.movie.az_movie.domain.enums.MovieType
import javax.inject.Inject

class MovieDataSource @Inject constructor(private val api: MovieApiService) {

    suspend fun getMoviesData(page: Int , type: MovieType) =
        api.getMoviesData(page , type = type.type)

    suspend fun getMoviesTopData(type: MovieType) = api.getMoviesTopData(type = type.type)

    suspend fun getMovie(movieId: Int) = api.getMovie(movieId)

}