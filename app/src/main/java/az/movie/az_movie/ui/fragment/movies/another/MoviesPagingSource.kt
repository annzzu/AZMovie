package az.movie.az_movie.ui.fragment.movies.another

import androidx.paging.PagingSource
import androidx.paging.PagingState
import az.movie.az_movie.data.repository.movie.MovieRepositoryImpl
import az.movie.az_movie.model.moviesDataModel.Movie
import az.movie.az_movie.util.response_handler.Resource

private const val STARTING_PAGE_INDEX = 1

class MoviesPagingSource(
    private val repository: MovieRepositoryImpl ,
    private val isMovie: Boolean
) :
    PagingSource<Int , Movie>() {

    override fun getRefreshKey(state: PagingState<Int , Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int , Movie> {
        val pageNumber = params.key ?: STARTING_PAGE_INDEX
        val response = if (isMovie)
            repository.getMovies(pageNumber , true)
        else
            repository.getMovies(pageNumber , false)

        return when (response) {
            is Resource.Success -> {
                LoadResult.Page(
                    data = response.data!!.data!!,
                    prevKey =  if (pageNumber == 1) null else pageNumber - 1,
                    nextKey =  if (response.data.meta!!.pagination!!.total!! > pageNumber) pageNumber + 1 else null
                )
            }
            is Resource.Error -> {
                LoadResult.Error(Throwable())
            }
            is Resource.Loading-> {
                LoadResult.Error(Throwable())
            }
        }
    }

}