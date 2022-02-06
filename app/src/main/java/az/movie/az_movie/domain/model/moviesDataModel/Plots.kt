package az.movie.az_movie.domain.model.moviesDataModel

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




