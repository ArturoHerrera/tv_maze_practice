package com.arthur.tv_maze.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TvShowDetailResponseDto(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("language") var language: String? = null,
    @SerializedName("genres") var genres: List<String> = listOf(),
    @SerializedName("status") var status: String? = null,
    @SerializedName("runtime") var runtime: Int? = null,
    @SerializedName("averageRuntime") var averageRuntime: Int? = null,
    @SerializedName("premiered") var premiered: String? = null,
    @SerializedName("ended") var ended: String? = null,
    @SerializedName("officialSite") var officialSite: String? = null,
    @SerializedName("schedule") var schedule: Schedule? = Schedule(),
    @SerializedName("rating") var rating: Rating? = Rating(),
    @SerializedName("weight") var weight: Int? = null,
    @SerializedName("network") var network: Network? = Network(),
    @SerializedName("webChannel") var webChannel: String? = null,
    @SerializedName("dvdCountry") var dvdCountry: String? = null,
    @SerializedName("externals") var externals: Externals? = Externals(),
    @SerializedName("image") var image: Image? = Image(),
    @SerializedName("summary") var summary: String? = null,
    @SerializedName("updated") var updated: Int? = null,
    @SerializedName("_links") var Links: Links? = Links()
)