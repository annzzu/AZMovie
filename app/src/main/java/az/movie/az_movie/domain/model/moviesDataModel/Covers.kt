package az.movie.az_movie.domain.model.moviesDataModel

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Covers(
    val data: Data?
){
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "1050")
        val size1050: String?,
        @Json(name = "145")
        val size145: String?,
        @Json(name = "1920")
        val size1920: String?,
        @Json(name = "367")
        val size367: String?,
        @Json(name = "510")
        val size510: String?,
        val blurhash: String?,
        val imageHeight: Any?,
        val position: String?,
        val positionPercentage: String?
    )
}