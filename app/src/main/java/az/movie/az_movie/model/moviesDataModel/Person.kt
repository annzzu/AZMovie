package az.movie.az_movie.model.moviesDataModel


import az.movie.az_movie.extensions.getNextLine
import com.squareup.moshi.Json

data class Person(
    val id: Int? ,
    val originalName: String? ,
    val poster: String? ,
    val primaryName: String? ,
    val tertiaryName: String? ,
) {
    val name: String?
        get() {
            val name = originalName ?: primaryName ?: tertiaryName
            return name?.getNextLine()
        }
}