package az.movie.az_movie.ui.fragment.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.movie.az_movie.domain.model.moviesDataModel.ItemMovie
import az.movie.az_movie.domain.response_handler.Resource
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch


class PlayerViewModel: ViewModel() {
    var playerCurrentPositionL = 0L
}