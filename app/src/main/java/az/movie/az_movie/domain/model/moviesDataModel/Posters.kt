package az.movie.az_movie.domain.model.moviesDataModel

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Posters(
    val data: Data?
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "240")
        val size240: String?,
    )
}