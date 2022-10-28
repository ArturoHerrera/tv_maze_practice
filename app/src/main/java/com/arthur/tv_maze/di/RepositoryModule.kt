package com.arthur.tv_maze.di

import com.arthur.tv_maze.data.remote.api.TvMazeApi
import com.arthur.tv_maze.data.repository.tv_list_repository.remote_data_source.TvListRetrofitRemoteDataSource
import com.arthur.tv_maze.data.repository.tv_list_repository.repositorys.TvListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @ViewModelScoped
    @Provides
    fun providesTvListGoodRepository(
        tvMazeApi: TvMazeApi
    ): TvListRepository = TvListRepository(
        tvListRemoteDS = TvListRetrofitRemoteDataSource(
            tvMazeApi = tvMazeApi
        )
    )

}