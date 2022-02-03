package az.movie.az_movie.model.moviesDataModel


data class Countries(
    val data: List<Data>?
){
    data class Data(
        val id: Int?,
        val primaryName: String?,
        val secondaryName: String?,
        val tertiaryName: String?
    )
}