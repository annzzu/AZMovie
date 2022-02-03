package az.movie.az_movie.data.remote.datasources.trailer

import az.movie.az_movie.data.remote.services.trailers.TrailersApiService
import javax.inject.Inject

class TrailerDataSource @Inject constructor(private val api: TrailersApiService) {


    suspend fun getTrailersData() = api.getTrailersData()

}