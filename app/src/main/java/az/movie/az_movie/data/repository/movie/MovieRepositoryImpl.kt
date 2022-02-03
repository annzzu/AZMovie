package az.movie.az_movie.data.repository.movie

import android.util.Log
import az.movie.az_movie.data.remote.datasources.movie.MovieDataSource
import az.movie.az_movie.model.moviesDataModel.MovieData
import az.movie.az_movie.util.MovieType
import az.movie.az_movie.util.response_handler.Resource
import az.movie.az_movie.util.response_handler.handleResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val dataSource: MovieDataSource) {

    private val searchResults = MutableSharedFlow<Resource<MovieData>>(replay = 1)

    suspend fun getMovies(page: Int , isMovie: Boolean): Resource<MovieData> {
        Log.d("GithubRepository" , "New query: $page")
        requestAndSaveData(page , isMovie)
        return if (isMovie)
            handleResponse { dataSource.getMoviesData(page , MovieType.MOVIE) }
        else
            handleResponse { dataSource.getMoviesData(page , MovieType.SERIES) }
    }

    private suspend fun requestAndSaveData(page: Int , isMovie: Boolean) {
        val response = if (isMovie)
            handleResponse { dataSource.getMoviesData(page , MovieType.MOVIE) }
        else
            handleResponse { dataSource.getMoviesData(page , MovieType.SERIES) }
        searchResults.emit(response)
    }




    fun getMoviesTopData(isMovie: Boolean): Flow<Resource<MovieData>> {
        return flow {
            emit(Resource.Loading())
            val result = if (isMovie)
                handleResponse { dataSource.getMoviesTopData(MovieType.MOVIE) }
            else
                handleResponse { dataSource.getMoviesTopData(MovieType.SERIES) }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
}