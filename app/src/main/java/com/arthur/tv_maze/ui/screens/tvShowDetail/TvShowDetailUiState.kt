package com.arthur.tv_maze.ui.screens.tvShowDetail

import com.arthur.tv_maze.data.model.TvShowActorSimple
import com.arthur.tv_maze.data.model.TvShowDetailSimple

data class TvShowDetailUiState(
    val loading: Boolean = false,
    val errorMsg: String? = null,
    val errorCastMsg: String? = null,
    val tvShowDetail: TvShowDetailSimple? = null,
    val tvShowCast: List<TvShowActorSimple> = emptyList()
)