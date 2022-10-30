package com.arthur.tv_maze.ui.screens.tvShowDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arthur.tv_maze.data.repository.tv_detail_repository.repositorys.TvShowDetailTasks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class TvShowDetailViewModel @Inject constructor(
    private val tvDetailsTasks: TvShowDetailTasks
) : ViewModel() {

    private val vmUiState = MutableStateFlow(TvShowDetailUiState())

    val uiState = vmUiState.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        vmUiState.value
    )

    init {
        getTvShowList()
        getTvShowCastList()
    }

    fun getTvShowList() {
        vmUiState.update { it.copy(loading = true) }
        viewModelScope.launch {
            tvDetailsTasks.getTvShowDetail().collect { tvShowDetail ->
                vmUiState.update {
                    it.copy(
                        loading = false,
                        errorMsg = tvShowDetail.errorMessage,
                        tvShowDetail = tvShowDetail.tvShowDetail
                    )
                }
            }
        }
    }

    fun getTvShowCastList() {
        viewModelScope.launch {
            tvDetailsTasks.getTvShowActorList().collect { tvShowCast ->
                vmUiState.update {
                    it.copy(
                        errorCastMsg = tvShowCast.errorMessage,
                        tvShowCast = tvShowCast.tvShowActorList
                    )
                }
            }
        }
    }

    fun clearErrorMsg() {
        vmUiState.update { it.copy(errorMsg = null) }
    }

    fun clearErrorCastMsg() {
        vmUiState.update { it.copy(errorCastMsg = null) }
    }

}