package az.movie.az_movie.domain.model.moviesDataModel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Seasons(
    val data: List<Season>?
) : Parcelable {

    fun clickSeason(seasonNumber: Int) {
        data?.forEach { season -> season.choose = season.number == seasonNumber }
    }

    fun lastIndex() = data?.indexOfLast { season -> season.choose }

    fun lastNum() = data?.findLast { season -> season.choose }?.number

}