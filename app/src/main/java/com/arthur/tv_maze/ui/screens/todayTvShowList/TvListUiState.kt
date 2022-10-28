package com.arthur.tv_maze.ui.screens.todayTvShowList

import com.arthur.tv_maze.data.model.TvShowSimple

data class TvListUiState(
    val loading: Boolean = false,
    val activeSearch: Boolean = false,
    val errorMessage: String? = null,
    val query: String = "",
    val todayTvShowList: List<TvShowSimple> = emptyList(),
    val finderTvShowList: List<TvShowSimple> = emptyList()
)