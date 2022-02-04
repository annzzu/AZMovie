package az.movie.az_movie.model.moviesDataModel

import az.movie.az_movie.model.moviesDataModel.Movie
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ItemMovie (
    val data: Movie
)
