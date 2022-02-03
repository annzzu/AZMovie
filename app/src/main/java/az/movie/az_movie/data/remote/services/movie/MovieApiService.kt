package az.movie.az_movie.data.remote.services.movie

import az.movie.az_movie.model.moviesDataModel.MovieData
import az.movie.az_movie.util.endpoints.MovieApiEndpoints
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface MovieApiService {

    @Headers("User-Agent: Retrofit-Sample-App")
    @GET(MovieApiEndpoints.MOVIES)
    suspend fun getMoviesData(
        @Query("page") page: Int = 1 ,
        @Query("filters[language]") language: String = "ENG" ,
        @Query("filters[type]") type: String = "movie" ,
        @Query("sort") sort: String = "-upload_date" ,
    ): Response<MovieData>

}