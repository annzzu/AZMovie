package az.movie.az_movie.domain.model.trailerDataModel

import az.movie.az_movie.domain.model.moviesDataModel.*
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Trailer(
    val id: Int ,
    val adjaraId: Int?  = null ,
    val primaryName: String?  = null ,
    val secondaryName: String?  = null ,
    val originalName: String?  = null ,
    val posters: Posters? = null ,
    val trailers: Trailers? = null ,
    val covers: Covers? = null ,
)

