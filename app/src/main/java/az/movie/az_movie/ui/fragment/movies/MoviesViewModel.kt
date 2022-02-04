package az.movie.az_movie.ui.fragment.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import az.movie.az_movie.data.repository.movie.MovieRepositoryImpl
import az.movie.az_movie.model.moviesDataModel.MovieData
import az.movie.az_movie.ui.fragment.movies_full_data.another.MoviesPagingSource
import az.movie.az_movie.util.response_handler.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MoviesViewModel @Inject constructor(private val repository: MovieRepositoryImpl) : ViewModel() {
    fun moviesFlow(isMovie:Boolean) =
        Pager(config = PagingConfig(pageSize = 1, maxSize = 20) ,
            pagingSourceFactory = { MoviesPagingSource(repository, isMovie) })
            .flow.cachedIn(viewModelScope)


    private val _movieTopData = MutableSharedFlow<Resource<MovieData>>()
    val movieTopData: SharedFlow<Resource<MovieData>> = _movieTopData

    suspend fun getMoviesTopData(isMovie: Boolean) = viewModelScope.launch {
        repository.getMoviesTopData(isMovie).collectLatest { values ->
            _movieTopData.emit(values)
        }
    }

}