package az.movie.az_movie.data.repository.search

import az.movie.az_movie.model.moviesDataModel.MovieData
import az.movie.az_movie.util.response_handler.Resource
import kotlinx.coroutines.flow.Flow


interface SearchRepository {

    suspend fun getSearch(keywords: String): Flow<Resource<MovieData>>

}