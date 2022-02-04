package az.movie.az_movie.model.moviesDataModel


import com.squareup.moshi.Json

data class Season(
    val episodesCount: Int? ,
    val movieId: Int? ,
    val name: String? ,
    val number: Int? ,
    val upcomingEpisodesCount: Int?
)