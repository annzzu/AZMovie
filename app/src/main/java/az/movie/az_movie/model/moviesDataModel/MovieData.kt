package az.movie.az_movie.model.moviesDataModel

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieData(
    val data: List<Movie>?,
    val meta: Meta?
)