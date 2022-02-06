package az.movie.az_movie.data.remote.services.search

import az.movie.az_movie.model.moviesDataModel.MovieData
import az.movie.az_movie.util.endpoints.MovieApiEndpoints
import az.movie.az_movie.util.response_handler.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface SearchApiService {

    @GET(MovieApiEndpoints.SEARCH)
    suspend fun getSearch(
        @Query("keywords") keywords: String ,
        @Query("page") page: Int = 1 ,
        @Query("source") source: String = "adjaranet"
    ): Response<MovieData>

}