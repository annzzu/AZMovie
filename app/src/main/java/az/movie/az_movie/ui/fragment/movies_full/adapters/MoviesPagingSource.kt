package az.movie.az_movie.ui.fragment.movies_full.adapters

import androidx.paging.PagingSource
import androidx.paging.PagingState
import az.movie.az_movie.data.remote.datasources.movie.MovieDataSource
import az.movie.az_movie.model.enums.MovieType
import az.movie.az_movie.model.moviesDataModel.Movie


private const val STARTING_PAGE_INDEX = 1

class MoviesPagingSource(
    private val service: MovieDataSource ,
    private val isMovie: Boolean ,
    private val periodType: String
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

        return try {
            val response = service.getMoviesPeriodTopData(
                page = pageNumber ,
                type = if (isMovie) MovieType.MOVIE else MovieType.SERIES ,
                period = periodType
            )

            LoadResult.Page(
                data = response.data!! ,
                prevKey = if (pageNumber == 1) null else pageNumber - 1 ,
                nextKey = if (response.meta!!.pagination!!.total!! > pageNumber) pageNumber + 1 else null
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}