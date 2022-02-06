package az.movie.az_movie.model.playerDataModel

import az.movie.az_movie.model.enums.LangType
import az.movie.az_movie.model.enums.QualityType
import com.google.android.exoplayer2.text.Subtitle
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlayerData(
    val data: List<EpisodePlayer>?
) {

    private fun getEpisodeWithIndex(index: Int): EpisodePlayer? = data?.get(index)

    val firstEpisode: EpisodePlayer?
        get() {
            return getEpisodeWithIndex(0)
        }

}

@JsonClass(generateAdapter = true)
data class EpisodePlayer(
    val episode: Int? ,
    val covers: Covers? ,
    val description: String? ,
    @Json(name = "file_will_be_added_soon")
    val fileWillBeAddedSoon: Boolean? ,
    val files: List<File>? ,
    val title: String? ,
) {
    val episodeTitle: String?
        get() {
            return "$episode. $title"
        }

    val cover: String?
        get() {
            return covers?.x1920 ?: covers?.x1050 ?: covers?.x510 ?: covers?.x367
            ?: covers?.x145
        }

    val hasFiles: Boolean
        get() {
            return !files.isNullOrEmpty()
        }

    fun subtitleUrl(lang: String?): String? {
        val subtitle = files?.findLast { file ->
            !file.subtitleSetUrl(lang).isNullOrBlank()
        }?.subtitleSetUrl(lang) ?: files?.findLast { file ->
            !file.subtitleEngUrl.isNullOrBlank()
        }?.subtitleSetUrl(lang)
        return subtitle
    }

    @JsonClass(generateAdapter = true)
    data class Covers(
        @Json(name = "1050")
        val x1050: String? ,
        @Json(name = "145")
        val x145: String? ,
        @Json(name = "1920")
        val x1920: String? ,
        @Json(name = "367")
        val x367: String? ,
        @Json(name = "510")
        val x510: String?
    )

    @JsonClass(generateAdapter = true)
    data class File(
        val files: List<FileX> ,
        val lang: String? ,
        val subtitles: List<Subtitle>? ,
    ) {
        val file: String?
            get() {
                return files.filter { file -> file.quality == QualityType.HIGH.name }[0].src
                    ?: files.filter { file -> file.quality == QualityType.MEDIUM.name }[0].src
            }

        val subtitleEngUrl: String?
            get() {
                return subtitles?.findLast { subtitle ->
                    LangType.ENG.name.lowercase() == subtitle.lang?.uppercase()
                }?.url
            }

        fun subtitleSetUrl(language: String?): String? =
            subtitles?.findLast { subtitle -> language == subtitle.lang?.uppercase() }?.url

        @JsonClass(generateAdapter = true)
        data class Subtitle(
            val url: String? ,
            val lang: String? ,
        )

        @JsonClass(generateAdapter = true)
        data class FileX(
            val id: Int? ,
            val quality: String ,
            val duration: Int? ,
            val src: String? ,
        )
    }
}
