package az.movie.az_movie.data.remote.datasources.movie

import az.movie.az_movie.data.remote.services.movie.MovieApiService
import az.movie.az_movie.model.enums.MoviePeriodType
import az.movie.az_movie.model.enums.MovieType
import javax.inject.Inject

class MovieDataSource @Inject constructor(private val api: MovieApiService) {

    suspend fun getMoviesData(page: Int , type: MovieType) =
        api.getMoviesData(page , type = type.type)

    suspend fun getMoviesTopData(type: MovieType) = api.getMoviesTopData(type = type.type)
    
    suspend fun getMoviesPeriodTopData(page: Int , type: MovieType , period: String) =
        api.getMoviesPeriodTopData(page , type = type.type , period = period)

    suspend fun getMovie(movieId: Int) = api.getMovie(movieId)

}