package com.arthur.tv_maze.di

import com.arthur.tv_maze.data.repository.tv_detail_repository.repositorys.TvDetailRepository
import com.arthur.tv_maze.data.repository.tv_detail_repository.repositorys.TvShowDetailTasks
import com.arthur.tv_maze.data.repository.tv_list_repository.repositorys.TvListRepository
import com.arthur.tv_maze.data.repository.tv_list_repository.repositorys.TvShowListTasks
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class InterfaceTasksModule {

    @Binds
    abstract fun bindsTvMazeTasks(
        repository: TvListRepository
    ): TvShowListTasks


    @Binds
    abstract fun bindsTvShowDetailTasks(
        repository: TvDetailRepository
    ): TvShowDetailTasks


}