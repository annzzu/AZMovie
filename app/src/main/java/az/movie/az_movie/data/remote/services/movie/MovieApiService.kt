package az.movie.az_movie.data.remote.services.movie

import az.movie.az_movie.domain.model.moviesDataModel.ItemMovie
import az.movie.az_movie.domain.model.moviesDataModel.MovieData
import az.movie.az_movie.util.endpoints.MovieApiEndpoints
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieApiService {

    @GET(MovieApiEndpoints.MOVIES)
    suspend fun getMoviesData(
        @Query("page") page: Int = 1 ,
        @Query("filters[language]") language: String = "ENG" ,
        @Query("filters[type]") type: String = "movie" ,
        @Query("filters[with_actors]") with_actors: Int = 1 ,
        @Query("filters[with_directors]") with_directors: Int = 1 ,
        @Query("sort") sort: String = "-upload_date" ,
        @Query("source") source: String = "adjaranet"
    ): Response<MovieData>

    @GET(MovieApiEndpoints.MOVIES_TOP)
    suspend fun getMoviesTopData(
        @Query("page") page: Int = 1 ,
        @Query("per_page") per_page: Int = 15 ,
        @Query("type") type: String = "movie" ,
        @Query("period") period: String = "day" ,
        @Query("filters[with_actors]") with_actors: Int = 1 ,
        @Query("filters[with_directors]") with_directors: Int = 1 ,
        @Query("filters[language]") language: String = "ENG" ,
        @Query("sort") sort: String = "-upload_date" ,
        @Query("source") source: String = "adjaranet" ,
    ): Response<MovieData>

    @GET(MovieApiEndpoints.MOVIE_ID)
    suspend fun getMovie(
        @Path("id") id: Int,
        @Query("filters[with_actors]") with_actors: Int = 4 ,
        @Query("filters[with_directors]") with_directors: Int = 2 ,
    ): Response<ItemMovie>
}