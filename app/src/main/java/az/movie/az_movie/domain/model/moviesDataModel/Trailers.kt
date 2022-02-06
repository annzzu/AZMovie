package az.movie.az_movie.domain.model.moviesDataModel

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Trailers(
    val data: List<Data>?
){
    @JsonClass(generateAdapter = true)
    data class Data(
        val fileUrl: String?,
        val id: Int?,
        val language: String?,
        val name: String?
    )
}