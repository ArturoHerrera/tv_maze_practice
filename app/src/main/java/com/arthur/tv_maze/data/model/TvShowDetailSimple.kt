package com.arthur.tv_maze.data.model

import com.arthur.tv_maze.utils.StringUtils

class TvShowDetailSimple private constructor(
    val name: String = "",
    val networkName: String = "",
    val coverMiniUrl: String = "",
    val coverBigUrl: String = "",
    val rating: String = "",
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
        var rating: String = "",
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

        fun setRaing(rating: Double?) =
            apply { this.rating = rating?.toString() ?: "" }

        fun setOfficialSiteUrl(officialSiteUrl: String?) =
            apply { this.officialSiteUrl = officialSiteUrl ?: "" }

        fun setSummary(summary: String?) =
            apply { this.summary = summary ?: "--" }

        fun setGenres(genresList: List<String>) =
            apply { this.genres = StringUtils.turnStringListToUniqueWord(genresList) }

        fun setScheduleTimeDays(time: String?, days: String?) =
            apply { this.name = "${time?.ifBlank { "--" } ?: "--"}  |  ${days?.ifBlank { "--" } ?: "--"}" }

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