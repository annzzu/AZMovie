package az.movie.az_movie.data.remote.datasources.search

import az.movie.az_movie.data.remote.services.search.SearchApiService
import javax.inject.Inject

class SearchDataSource @Inject constructor(private val api: SearchApiService) {

    suspend fun getSearch(keywords: String) = api.getSearch(keywords)

}