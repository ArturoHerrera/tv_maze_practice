package com.arthur.tv_maze.ui.screens.tvShowDetail

import com.arthur.tv_maze.data.model.TvShowDetailSimple

data class TvShowDetailUiState(
    val loading: Boolean = false,
    val errorMsg: String? = null,
    val tvShowDetail: TvShowDetailSimple? = null
)