package az.movie.az_movie.data.repository.movie

import androidx.paging.PagingData
import az.movie.az_movie.model.moviesDataModel.ItemMovie
import az.movie.az_movie.model.moviesDataModel.MovieData
import az.movie.az_movie.domain.response_handler.Resource
import az.movie.az_movie.model.moviesDataModel.Movie
import kotlinx.coroutines.flow.Flow


interface MovieRepository {

    suspend fun getMovies(page: Int , isMovie: Boolean): Resource<MovieData>

    fun getMoviesTopData(isMovie: Boolean): Flow<Resource<MovieData>>

    fun getMovie(movieId: Int): Flow<Resource<ItemMovie>>

    fun getMoviesPeriodTopData(isMovie: Boolean , periodType: String):
            Flow<PagingData<Movie>>
}