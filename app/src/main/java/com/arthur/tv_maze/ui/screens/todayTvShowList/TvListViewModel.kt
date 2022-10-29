package com.arthur.tv_maze.ui.screens.todayTvShowList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arthur.tv_maze.data.repository.tv_list_repository.repositorys.TvShowListTasks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@ExperimentalCoroutinesApi
@HiltViewModel
class TvListViewModel @Inject constructor(
    private val tvShowListTasks: TvShowListTasks
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
            tvShowListTasks.getTvMazeShowList().collect { todayTvShow ->
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

    fun setActiveSearchState(state: Boolean) {
        vmUiState.update { it.copy(activeSearch = state) }
    }

    fun clearErrorMsg() {
        vmUiState.update { it.copy(errorMessage = null) }
    }

    fun clearQuery() {
        vmUiState.update { it.copy(query = "") }
    }

    fun filterTvShow(query: String) {
        if(uiState.value.todayTvShowList.isNotEmpty()){
            val mTvShowList = uiState.value.todayTvShowList.filter { it.name?.contains(query, true)!! }
            vmUiState.update { it.copy(finderTvShowList = mTvShowList) }
        }
    }
}