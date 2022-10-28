package com.arthur.tv_maze.data.model


class TvShowSimple private constructor(
    val id: Long? = null,
    val name: String? = null,
    val networkName: String? = null,
    val airTime: String? = null,
    val airDate: String? = null,
    val time: String? = null,
    val days: List<String>? = emptyList(),
    val posterUrl: String? = null
) {
    data class Builder(
        var id: Long? = null,
        var name: String? = null,
        var networkName: String? = null,
        var airTime: String? = null,
        var airDate: String? = null,
        var time: String? = null,
        var days: List<String>? = null,
        var posterUrl: String? = null,
        var error: String? = null
    ) {
        fun setId(id: Long?) = apply { this.id = id ?: -1 }

        fun setName(name: String?) = apply { this.name = name ?: "" }

        fun setNetworkName(networkName: String?) = apply { this.networkName = networkName ?: "" }

        fun setAirDate(airDate: String?) = apply { this.airDate = airDate ?: "" }

        fun setAirTime(airTime: String?) = apply { this.airTime = airTime ?: "" }

        fun setTime(time: String?) = apply { this.time = time ?: "" }

        fun setDays(days: List<String>?) = apply { this.days = days ?: emptyList() }

        fun setPosterUrl(posterUrl: String?) = apply { this.posterUrl = posterUrl ?: "" }

        fun setError(error: String?) = apply { this.error = error ?: "Error desconocido" }

        fun build() = TvShowSimple(
            id = id,
            name = name,
            networkName = networkName,
            airTime = airTime,
            airDate = airDate,
            days = days,
            time = time,
            posterUrl = posterUrl
        )
    }
}

data class TodayTvShowList(
    val errorMessage: String? = null,
    val TvShowSimpleList: List<TvShowSimple>
)