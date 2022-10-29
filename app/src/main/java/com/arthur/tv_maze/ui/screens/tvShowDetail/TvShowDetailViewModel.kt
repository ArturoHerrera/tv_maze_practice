package com.arthur.tv_maze.ui.screens.tvShowDetail

import androidx.lifecycle.ViewModel
import com.arthur.tv_maze.data.repository.tv_detail_repository.repositorys.TvShowDetailTasks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class TvShowDetailViewModel @Inject constructor(
    private val tvDetailsTasks: TvShowDetailTasks
) : ViewModel() {

}