package az.movie.az_movie.data.remote.services.trailers

import az.movie.az_movie.data.remote.datasources.trailer.TrailerDataSource
import az.movie.az_movie.model.moviesDataModel.MovieData
import az.movie.az_movie.model.trailerDataModel.TrailerData
import az.movie.az_movie.util.endpoints.MovieApiEndpoints
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface TrailersApiService {

    @Headers("User-Agent: Retrofit-Sample-App")
    @GET(MovieApiEndpoints.TRAILERS)
    suspend fun getTrailersData(): Response<TrailerData>

}