package com.arthur.tv_maze.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ActorResponseDto(
    @SerializedName("person"    ) var person    : Person?    = Person(),
    @SerializedName("character" ) var character : Character? = Character(),
    @SerializedName("self"      ) var self      : Boolean?   = null,
    @SerializedName("voice"     ) var voice     : Boolean?   = null
)

data class Person (
    @SerializedName("id"       ) var id       : Int?     = null,
    @SerializedName("url"      ) var url      : String?  = null,
    @SerializedName("name"     ) var name     : String?  = null,
    @SerializedName("country"  ) var country  : Country? = Country(),
    @SerializedName("birthday" ) var birthday : String?  = null,
    @SerializedName("deathday" ) var deathday : String?  = null,
    @SerializedName("gender"   ) var gender   : String?  = null,
    @SerializedName("image"    ) var image    : Image?   = Image(),
    @SerializedName("updated"  ) var updated  : Int?     = null,
    @SerializedName("_links"   ) var Links    : Links?   = Links()
)

data class Character (
    @SerializedName("id"     ) var id    : Int?    = null,
    @SerializedName("url"    ) var url   : String? = null,
    @SerializedName("name"   ) var name  : String? = null,
    @SerializedName("image"  ) var image : Image?  = Image(),
    @SerializedName("_links" ) var Links : Links?  = Links()
)