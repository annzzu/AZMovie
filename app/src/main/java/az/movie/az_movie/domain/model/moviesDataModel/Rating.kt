package az.movie.az_movie.domain.model.moviesDataModel

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Rating(
    val imdb: Imdb?,
    val imovies: Imovies?,
    val metacritic: Metacritic?,
    val rotten: Rotten?
) {
    @JsonClass(generateAdapter = true)
    data class Imdb(
        val score: Int?,
        val voters: Int?
    )

    @JsonClass(generateAdapter = true)
    data class Imovies(
        val score: Int?,
        val voters: Int?
    )

    @JsonClass(generateAdapter = true)
    data class Metacritic(
        val score: Int?,
        val voters: Any?
    )

    @JsonClass(generateAdapter = true)
    data class Rotten(
        val score: Int?,
        val voters: Any?
    )
}