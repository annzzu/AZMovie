package az.movie.az_movie.ui.fragment.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.movie.az_movie.data.repository.movie.MovieRepositoryImpl
import az.movie.az_movie.domain.model.moviesDataModel.MovieData
import az.movie.az_movie.domain.response_handler.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MoviesViewModel @Inject constructor(private val repository: MovieRepositoryImpl) : ViewModel() {

    private val _movieTopData = MutableSharedFlow<Resource<MovieData>>()
    val movieTopData: SharedFlow<Resource<MovieData>> = _movieTopData

    suspend fun getMoviesTopData(isMovie: Boolean) = viewModelScope.launch {
        repository.getMoviesTopData(isMovie).collectLatest { values ->
            _movieTopData.emit(values)
        }
    }

}