package az.movie.az_movie.ui.fragment.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.movie.az_movie.data.repository.search.SearchRepositoryImpl
import az.movie.az_movie.domain.model.moviesDataModel.MovieData
import az.movie.az_movie.domain.response_handler.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: SearchRepositoryImpl) : ViewModel() {

    private val _searchMovie = MutableSharedFlow<Resource<MovieData>>()
    val searchMovie: SharedFlow<Resource<MovieData>> = _searchMovie

    suspend fun getSearch(keywords:String) = viewModelScope.launch {
        repository.getSearch(keywords).collectLatest { values ->
            _searchMovie.emit(values)
        }
    }

}