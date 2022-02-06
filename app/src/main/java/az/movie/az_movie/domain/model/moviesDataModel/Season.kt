package az.movie.az_movie.domain.model.moviesDataModel


import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Season(
    val episodesCount: Int? ,
    val movieId: Int? ,
    val name: String? ,
    val number: Int ,
    val upcomingEpisodesCount: Int?,
    var choose:  Boolean = false
) : Parcelable {
    @IgnoredOnParcel
    val title: String
        get() {
            return "Season $number"
        }

}