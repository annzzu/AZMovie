package az.movie.az_movie.data.remote.services.trailers

import az.movie.az_movie.domain.model.trailerDataModel.TrailerData
import az.movie.az_movie.util.endpoints.MovieApiEndpoints
import retrofit2.Response
import retrofit2.http.GET


interface TrailersApiService {

    @GET(MovieApiEndpoints.TRAILERS)
    suspend fun getTrailersData(): Response<TrailerData>

}