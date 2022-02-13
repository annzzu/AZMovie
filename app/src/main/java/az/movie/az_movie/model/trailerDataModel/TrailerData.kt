package az.movie.az_movie.model.trailerDataModel

import az.movie.az_movie.model.moviesDataModel.Meta
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TrailerData(
    val data: List<Trailer>? ,
    val meta: Meta?,
)