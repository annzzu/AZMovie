package az.movie.az_movie.model.moviesDataModel

import az.movie.az_movie.model.moviesDataModel.Countries
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Movie(
    val id: Int ,
    val adjaraId: Int? ,
    val primaryName: String? ,
    val secondaryName: String? ,
    val originalName: String? ,
    val year: Int? ,
    val releaseDate: String? ,
    val imdbUrl: String? ,
    val duration: Int? ,
    val adult: Boolean? ,
    val cover: Cover? ,
    val poster: String? ,
    val languages: Languages? ,
    val posters: Posters? ,
    val covers: Covers? ,
    val plot: Plot? ,
    val plots: Plots?,
    val genres: Genres? ,
    val trailers: Trailers? ,
    val countries: Countries? ,
//    val seasons: Seasons?
)


