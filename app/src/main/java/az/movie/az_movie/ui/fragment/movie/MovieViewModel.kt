package az.movie.az_movie.ui.fragment.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import az.movie.az_movie.data.repository.movie.MovieRepositoryImpl
import az.movie.az_movie.ui.fragment.movies.another.MoviesPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: MovieRepositoryImpl) : ViewModel() {
    fun moviesFlow(isMovie:Boolean) =
        Pager(config = PagingConfig(pageSize = 1, maxSize = 20) ,
            pagingSourceFactory = { MoviesPagingSource(repository, isMovie) })
            .flow.cachedIn(viewModelScope)
}