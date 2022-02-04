package az.movie.az_movie.model.moviesDataModel

import az.movie.az_movie.model.moviesDataModel.Countries
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

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
    val languages: Languages? ,
    val posters: Posters? ,
    val covers: Covers? ,
    val plot: Plot? ,
    val plots: Plots? ,
    val genres: Genres? ,
    val trailers: Trailers? ,
    val countries: Countries? ,
    val seasons: Seasons?
) {

    val isMovie: Boolean?
        get() {
            return seasons?.data?.isNotEmpty() == true && seasons.data.size == 1
        }
    val title: String
        get() {
            return secondaryName ?: primaryName ?: originalName ?: ""
        }

    val poster: String?
        get() {
            return posters?.data?.size240 ?: covers?.data?.size1920 ?: covers?.data?.size1050
        }

    val coverR: String?
        get() {
            return covers?.data?.size1920 ?: covers?.data?.size1050?: posters?.data?.size240
        }

    val trailer: String?
        get() {
            return trailers?.data?.get(0)?.fileUrl
        }

}


