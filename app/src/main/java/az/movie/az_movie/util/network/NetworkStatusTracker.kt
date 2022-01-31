package az.movie.az_movie.util.network

import android.net.ConnectivityManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

interface NetworkStatusTracker {

    val connectivityManager: ConnectivityManager
    @ExperimentalCoroutinesApi
    val networkStatus: Flow<NetworkStatus>

}