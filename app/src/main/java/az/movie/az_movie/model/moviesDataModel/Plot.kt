package az.movie.az_movie.model.moviesDataModel

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Plot(
    val data: Data?
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        val description: String?,
        val language: String?
    )
}