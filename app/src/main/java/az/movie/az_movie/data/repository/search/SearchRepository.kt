package az.movie.az_movie.data.repository.search

import az.movie.az_movie.domain.model.moviesDataModel.MovieData
import az.movie.az_movie.domain.response_handler.Resource
import kotlinx.coroutines.flow.Flow


interface SearchRepository {

    suspend fun getSearch(keywords: String): Flow<Resource<MovieData>>

}