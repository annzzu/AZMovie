package az.movie.az_movie.model.moviesDataModel

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Meta(
    val pagination: Pagination?
){
    @JsonClass(generateAdapter = true)
    data class Pagination(
        val count: Int?,
        @Json(name = "current_page")
        val currentPage: Int?,
        @Json(name = "per_page")
        val perPage: Int?,
        val total: Int?,
        @Json(name = "total_pages")
        val totalPages: Int?
    )
}