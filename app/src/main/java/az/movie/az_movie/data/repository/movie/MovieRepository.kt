package az.movie.az_movie.data.repository.movie

import az.movie.az_movie.model.moviesDataModel.ItemMovie
import az.movie.az_movie.model.moviesDataModel.MovieData
import az.movie.az_movie.util.response_handler.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow



interface MovieRepository {

    suspend fun getMovies(page: Int , isMovie: Boolean): Resource<MovieData>

    suspend fun requestAndSaveData(page: Int , isMovie: Boolean)

    val searchResults : MutableSharedFlow<Resource<MovieData>>

    fun getMoviesTopData(isMovie: Boolean): Flow<Resource<MovieData>>

    fun getMovie(movieId: Int): Flow<Resource<ItemMovie>>

}