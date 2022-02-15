package az.movie.az_movie.data.repository.movie

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import az.movie.az_movie.model.moviesDataModel.ItemMovie
import az.movie.az_movie.data.remote.datasources.movie.MovieDataSource
import az.movie.az_movie.model.moviesDataModel.MovieData
import az.movie.az_movie.model.enums.MovieType
import az.movie.az_movie.domain.response_handler.Resource
import az.movie.az_movie.domain.response_handler.handleResponse
import az.movie.az_movie.model.enums.MoviePeriodType
import az.movie.az_movie.model.moviesDataModel.Movie
import az.movie.az_movie.ui.fragment.movies_full.adapters.MoviesPagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val dataSource: MovieDataSource) :
    MovieRepository {

    override suspend fun getMovies(page: Int , isMovie: Boolean): Resource<MovieData> {
        return if (isMovie)
            handleResponse { dataSource.getMoviesData(page , MovieType.MOVIE) }
        else
            handleResponse { dataSource.getMoviesData(page , MovieType.SERIES) }
    }

    override fun getMoviesTopData(isMovie: Boolean): Flow<Resource<MovieData>> {
        return flow {
            emit(Resource.Loading())
            val result = if (isMovie)
                handleResponse { dataSource.getMoviesTopData(MovieType.MOVIE) }
            else
                handleResponse { dataSource.getMoviesTopData(MovieType.SERIES) }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    override fun getMovie(movieId: Int): Flow<Resource<ItemMovie>> {
        return flow {
            emit(handleResponse { dataSource.getMovie(movieId) })
        }.flowOn(Dispatchers.IO)
    }

    override fun getMoviesPeriodTopData(
        isMovie: Boolean ,
        periodType: String
    ): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 1 , enablePlaceholders = false) ,
            pagingSourceFactory = { MoviesPagingSource(dataSource , isMovie , periodType) }
        ).flow.debounce(500)
    }

}