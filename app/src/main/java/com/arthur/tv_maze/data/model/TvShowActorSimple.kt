package com.arthur.tv_maze.data.model

class TvShowActorSimple private constructor(
    val actorName: String = "",
    val actorPhotoUrl: String = ""
) {
    data class Builder(
        var actorName: String = "",
        var actorPhotoUrl: String = ""
    ){
        fun setActorName(actorName: String?) =
            apply { this.actorName = actorName ?: "--" }

        fun setActorPhotoUrl(actorPhotoUrl: String?) =
            apply { this.actorPhotoUrl = actorPhotoUrl ?: "--" }

        fun build() = TvShowActorSimple(
            actorName = actorName,
            actorPhotoUrl = actorPhotoUrl
        )
    }
}

data class TvShowActor(
    val errorMessage: String? = null,
    val tvShowActorList: List<TvShowActorSimple> = emptyList()
)