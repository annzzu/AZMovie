package az.movie.az_movie.ui.fragment.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.movie.az_movie.model.moviesDataModel.ItemMovie
import az.movie.az_movie.data.repository.movie.MovieRepositoryImpl
import az.movie.az_movie.util.response_handler.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: MovieRepositoryImpl) : ViewModel() {
    private val _movie = MutableSharedFlow<Resource<ItemMovie>>()
    val movie: SharedFlow<Resource<ItemMovie>> = _movie

    suspend fun getMovie(movieId: Int) = viewModelScope.launch {
        repository.getMovie(movieId).collectLatest { values ->
            _movie.emit(values)
        }
    }
}