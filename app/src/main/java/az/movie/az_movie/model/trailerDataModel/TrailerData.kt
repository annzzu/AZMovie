package az.movie.az_movie.model.trailerDataModel

import az.movie.az_movie.model.moviesDataModel.Meta
import az.movie.az_movie.model.moviesDataModel.Movie
import az.movie.az_movie.ui.fragment.trailer.TrailerSlide
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TrailerData(
    val data: List<Trailer>? ,
    val meta: Meta?,
    var trailerSlide: TrailerSlide?
)