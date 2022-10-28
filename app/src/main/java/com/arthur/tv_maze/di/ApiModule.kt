package com.arthur.tv_maze.di

import com.arthur.tv_maze.data.remote.api.TvMazeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit


@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun provideTvMazeService(retrofit: Retrofit): TvMazeApi = retrofit.create(TvMazeApi::class.java)

}