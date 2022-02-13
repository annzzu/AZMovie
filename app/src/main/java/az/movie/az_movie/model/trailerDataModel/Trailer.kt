package az.movie.az_movie.model.trailerDataModel

import az.movie.az_movie.model.moviesDataModel.*
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Trailer(
    val id: Int ,
    val adjaraId: Int? = null ,
    val primaryName: String? = null ,
    val secondaryName: String? = null ,
    val originalName: String? = null ,
    val posters: Posters? = null ,
    val trailers: Trailers? = null ,
    val covers: Covers? = null ,
) {
    val title: String?
        get() {
            return secondaryName ?: primaryName ?: originalName
        }
    val cover: String?
        get() {
            return covers?.data?.size1920 ?: covers?.data?.size1050 ?: posters?.data?.size240
        }
}

