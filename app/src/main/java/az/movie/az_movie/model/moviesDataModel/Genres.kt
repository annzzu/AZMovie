package az.movie.az_movie.model.moviesDataModel


data class Genres(
    val data: List<Data>?
){
    data class Data(
        val id: Int?,
        val backgroundImage: String?,
        val primaryName: String?,
        val secondaryName: String?,
        val tertiaryName: String?
    )
}