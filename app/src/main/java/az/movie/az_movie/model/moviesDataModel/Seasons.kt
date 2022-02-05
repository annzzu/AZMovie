package az.movie.az_movie.model.moviesDataModel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Seasons(
    val data: List<Season>?
) : Parcelable {

    fun clickSeason(seasonNumber: Int) {
        data?.findLast { season -> season.number == seasonNumber }?.choose = true
        data?.findLast { season -> season.number != seasonNumber }?.choose = false
    }

    fun findChosen() = data?.indexOfLast { season -> season.choose }
    fun findChosenNum() = data?.findLast { season -> season.choose }?.number

}