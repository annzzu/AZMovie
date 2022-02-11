package az.movie.az_movie.ui.fragment.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.movie.az_movie.domain.model.moviesDataModel.ItemMovie
import az.movie.az_movie.data.repository.movie.MovieRepositoryImpl
import az.movie.az_movie.domain.response_handler.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: MovieRepositoryImpl) : ViewModel() {
    private val _movie = MutableStateFlow<Resource<ItemMovie>>(Resource.Loading())
    val movie: StateFlow<Resource<ItemMovie>> = _movie

    suspend fun getMovie(movieId: Int) = viewModelScope.launch {
        repository.getMovie(movieId).collectLatest { values ->
            _movie.emit(values)
        }
    }
}