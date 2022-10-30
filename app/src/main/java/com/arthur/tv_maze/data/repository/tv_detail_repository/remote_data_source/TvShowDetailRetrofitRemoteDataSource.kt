package com.arthur.tv_maze.data.repository.tv_detail_repository.remote_data_source

import com.arthur.tv_maze.data.remote.api.TvMazeApi
import com.arthur.tv_maze.data.remote.dto.ActorResponseDto
import com.arthur.tv_maze.data.remote.dto.TvShowDetailResponseDto
import com.arthur.tv_maze.data.repository.tv_detail_repository.repositorys.TvDetailRemoteDataSource
import com.arthur.tv_maze.utils.ServiceResult
import com.arthur.tv_maze.utils.networkCall

class TvShowDetailRetrofitRemoteDataSource(
    private val tvMazeApi: TvMazeApi
) : TvDetailRemoteDataSource {

    override suspend fun getTvShowDetail(): ServiceResult<TvShowDetailResponseDto> =
        networkCall {
            tvMazeApi.getTvShowDetail().body()!!
        }

    override suspend fun getTvShowCast(): ServiceResult<List<ActorResponseDto>> =
        networkCall {
            tvMazeApi.getTvShowCast().body()!!
        }

}