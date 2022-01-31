package az.movie.az_movie.ui.fragment.network

import androidx.lifecycle.ViewModel
import az.movie.az_movie.util.network.NetworkStatusTrackerImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@HiltViewModel
class NetworkViewModel @Inject constructor(private val networkStatus: NetworkStatusTrackerImpl) :
    ViewModel() {

    @ExperimentalCoroutinesApi
    val networkStatusActive = networkStatus.networkStatus

}