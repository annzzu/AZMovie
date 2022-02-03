package az.movie.az_movie.ui.fragment.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import az.movie.az_movie.data.remote.datasources.trailer.TrailerDataSource
import az.movie.az_movie.data.repository.movie.MovieRepositoryImpl
import az.movie.az_movie.data.repository.trailer.TrailerRepositoryImpl
import az.movie.az_movie.model.trailerDataModel.TrailerData
import az.movie.az_movie.ui.fragment.movies.another.MoviesPagingSource
import az.movie.az_movie.util.response_handler.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TrailerViewModel @Inject constructor(private val repository: TrailerRepositoryImpl) : ViewModel() {

    private val _trailers = MutableSharedFlow<Resource<TrailerData>>()
    val trailers: SharedFlow<Resource<TrailerData>> = _trailers

    private suspend fun getTrailersData() = viewModelScope.launch {
        repository.getTrailersData().collectLatest { values ->
            _trailers.emit(values)
        }
    }
    init {
        viewModelScope.launch {
            getTrailersData()
        }
    }
}