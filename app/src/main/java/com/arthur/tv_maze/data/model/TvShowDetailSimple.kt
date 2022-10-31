package com.arthur.tv_maze.data.model

import com.arthur.tv_maze.utils.StringUtils

class TvShowDetailSimple private constructor(
    val name: String = "",
    val networkName: String = "",
    val coverMiniUrl: String = "",
    val coverBigUrl: String = "",
    val rating: Float = -1.0f,
    val officialSiteUrl: String = "",
    val summary: String = "",
    val genres: String = "",
    val scheduleTimeDays: String = ""
) {
    data class Builder(
        var name: String = "",
        var networkName: String = "",
        var coverMiniUrl: String = "",
        var coverBigUrl: String = "",
        var rating: Float = -1.0f,
        var officialSiteUrl: String = "",
        var summary: String = "",
        var genres: String = "",
        var scheduleTimeDays: String = ""
    ) {
        fun setName(name: String?) =
            apply { this.name = name ?: "--" }

        fun setNetworkName(networkName: String?) =
            apply { this.networkName = networkName ?: "--" }

        fun setCoverMiniUrl(coverMiniUrl: String?) =
            apply { this.coverMiniUrl = coverMiniUrl ?: "" }

        fun setCoverBigUrl(coverBigUrl: String?) =
            apply { this.coverBigUrl = coverBigUrl ?: "" }

        fun setRating(rating: Double?) =
            apply { this.rating = rating?.toFloat() ?: -1.0f }

        fun setOfficialSiteUrl(officialSiteUrl: String?) =
            apply { this.officialSiteUrl = officialSiteUrl ?: "" }

        fun setSummary(summary: String?) =
            apply { this.summary = summary ?: "--" }

        fun setGenres(genresList: List<String>) =
            apply { this.genres = StringUtils.returnWordOfArrayString(genresList, spacer = ",  ") }

        fun setScheduleTimeDays(time: String?, days: List<String>) =
            apply { this.scheduleTimeDays = "${time?.ifBlank { "--" } ?: "--"}  |  ${StringUtils.returnWordOfArrayString(days, 3)}" }

        fun build() = TvShowDetailSimple(
            name = name,
            networkName = networkName,
            coverMiniUrl = coverMiniUrl,
            coverBigUrl = coverBigUrl,
            rating = rating,
            officialSiteUrl = officialSiteUrl,
            summary = summary,
            genres = genres,
            scheduleTimeDays = scheduleTimeDays
        )
    }
}

data class TvShowDetail (
    val errorMessage: String? = null,
    val tvShowDetail: TvShowDetailSimple? = null
)