package az.movie.az_movie.model.moviesDataModel


data class Genres(
    val data: List<Data>?
){
    data class Data(
        val backgroundImage: String?,
        val id: Int?,
        val primaryName: String?,
        val secondaryName: String?,
        val tertiaryName: String?
    )
}