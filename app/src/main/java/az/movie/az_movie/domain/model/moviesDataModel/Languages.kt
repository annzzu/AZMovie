package az.movie.az_movie.domain.model.moviesDataModel

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Languages(
    val data: List<Data>?
){
    @JsonClass(generateAdapter = true)
    data class Data(
        val code: String?,
        val primaryName: String?,
        val primaryNameTurned: String?,
        val secondaryName: String?,
        val tertiaryName: String?
    )
}