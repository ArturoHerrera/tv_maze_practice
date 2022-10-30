package com.arthur.tv_maze.ui.screens.tvShowDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arthur.tv_maze.data.repository.tv_detail_repository.repositorys.TvShowDetailTasks
import com.arthur.tv_maze.ui.NavArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class TvShowDetailViewModel @Inject constructor(
    private val tvDetailsTasks: TvShowDetailTasks,
    savedStateHandle: SavedStateHandle?
) : ViewModel() {

    private val vmUiState =
        MutableStateFlow(TvShowDetailUiState())

    val uiState = vmUiState.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        vmUiState.value
    )

    private var tvShowId: Long? = savedStateHandle?.get(NavArgs.TV_SHOW_ID)

    init {
        tvShowId?.let { safeTvShowId ->
            getTvShowList(safeTvShowId)
            getTvShowCastList(safeTvShowId)
        } ?: kotlin.run {
            vmUiState.update { it.copy(errorMsg = "Hubo un problema al consultar la información.") }
        }
    }

    fun getTvShowList(tvShowId: Long) {
        vmUiState.update { it.copy(loading = true) }
        viewModelScope.launch {
            tvDetailsTasks.getTvShowDetail(tvShowId).collect { tvShowDetail ->
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

    fun getTvShowCastList(tvShowId: Long) {
        viewModelScope.launch {
            tvDetailsTasks.getTvShowActorList(tvShowId).collect { tvShowCast ->
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