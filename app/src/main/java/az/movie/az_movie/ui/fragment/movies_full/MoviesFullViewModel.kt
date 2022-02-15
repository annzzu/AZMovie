package az.movie.az_movie.ui.fragment.movies_full

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import az.movie.az_movie.data.repository.movie.MovieRepositoryImpl
import az.movie.az_movie.model.moviesDataModel.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject


@HiltViewModel
class MoviesFullViewModel @Inject constructor(private val repository: MovieRepositoryImpl) :
    ViewModel() {

    fun searchMovies(isMovie: Boolean , periodType: String):
            Flow<PagingData<Movie>> =
        repository.getMoviesPeriodTopData(isMovie , periodType).cachedIn(viewModelScope)

}