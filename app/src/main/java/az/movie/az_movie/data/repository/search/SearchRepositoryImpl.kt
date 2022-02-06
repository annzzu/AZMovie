package az.movie.az_movie.data.repository.search

import az.movie.az_movie.data.remote.datasources.search.SearchDataSource
import az.movie.az_movie.domain.model.moviesDataModel.MovieData
import az.movie.az_movie.domain.response_handler.Resource
import az.movie.az_movie.domain.response_handler.handleResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val dataSource: SearchDataSource) :
    SearchRepository {

    override suspend fun getSearch(keywords: String): Flow<Resource<MovieData>> {
        return flow {
            emit(Resource.Loading())
            delay(500)
            emit(handleResponse { dataSource.getSearch(keywords) })
        }.debounce(1000).flowOn(Dispatchers.IO)
    }

}