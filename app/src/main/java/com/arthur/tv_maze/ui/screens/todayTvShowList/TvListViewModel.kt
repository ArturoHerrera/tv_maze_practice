package com.arthur.tv_maze.ui.screens.todayTvShowList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arthur.tv_maze.data.repository.tv_list_repository.repositorys.TvMazeTasks
import com.arthur.tv_maze.ui.screens.todayTvShowList.TvListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@ExperimentalCoroutinesApi
@HiltViewModel
class TvListViewModel @Inject constructor(
    private val tvMazeTasks: TvMazeTasks
) : ViewModel() {

    private val vmUiState = MutableStateFlow(TvListUiState())

    val uiState = vmUiState.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        vmUiState.value
    )

    init {
        getTvShowList()
    }

    fun getTvShowList() {
        vmUiState.update { it.copy(loading = true) }
        viewModelScope.launch {
            tvMazeTasks.getTvMazeShowList().collect { todayTvShow ->
                vmUiState.update {
                    it.copy(
                        loading = false,
                        errorMessage = todayTvShow.errorMessage,
                        todayTvShowList = todayTvShow.TvShowSimpleList
                    )
                }
            }
        }
    }
}