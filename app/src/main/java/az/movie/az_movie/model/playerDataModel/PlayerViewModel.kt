package az.movie.az_movie.model.playerDataModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.movie.az_movie.data.repository.player.PlayerRepositoryImpl
import az.movie.az_movie.util.response_handler.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(private val repository: PlayerRepositoryImpl) :
    ViewModel() {

    private val _movie = MutableSharedFlow<Resource<PlayerData>>()
    val movie: SharedFlow<Resource<PlayerData>> = _movie

    suspend fun getMovie(movieId: Int , season: Int) = viewModelScope.launch {
        repository.getPlayerMovie(movieId, season).collectLatest { values ->
            _movie.emit(values)
        }
    }
}