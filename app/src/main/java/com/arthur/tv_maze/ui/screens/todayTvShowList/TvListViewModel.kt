package com.arthur.tv_maze.ui.screens.todayTvShowList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arthur.tv_maze.data.repository.tv_list_repository.repositorys.TvMazeTasks
import com.arthur.tv_maze.ui.screens.todayTvShowList.TvListUiState
import com.google.gson.Gson
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

    fun setActiveSearchState(state: Boolean) {
        vmUiState.update { it.copy(activeSearch = state) }
    }

    fun clearTvShowList() {
        vmUiState.update { it.copy(finderTvShowList = emptyList()) }
    }

    fun clearErrorMsg() {
        vmUiState.update { it.copy(errorMessage = null) }
    }

    fun setQuery(query: String) {
        vmUiState.update { it.copy(query = query) }
    }

    fun filterTvShow(query: String) {
        if(uiState.value.todayTvShowList.isNotEmpty()){
            val mTvShowList = uiState.value.todayTvShowList.filter { it.name?.contains(query, true)!! }
            vmUiState.update { it.copy(finderTvShowList = mTvShowList) }
        }
    }
}