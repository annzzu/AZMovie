package az.movie.az_movie.ui.fragment.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.movie.az_movie.data.repository.trailer.TrailerRepositoryImpl
import az.movie.az_movie.model.trailerDataModel.TrailerData
import az.movie.az_movie.domain.response_handler.Resource
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

    suspend fun getTrailersData() = viewModelScope.launch {
        repository.getTrailersData().collectLatest { values ->
            _trailers.emit(values)
        }
    }

}