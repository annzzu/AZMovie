package az.movie.az_movie.model.moviesDataModel

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Plots(
    val data: List<Data>?
){
    @JsonClass(generateAdapter = true)
    data class Data(
        val description: String?,
        val language: String?
    )
}