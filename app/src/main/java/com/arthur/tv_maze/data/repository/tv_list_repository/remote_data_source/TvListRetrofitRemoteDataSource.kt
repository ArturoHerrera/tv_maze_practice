package com.arthur.tv_maze.data.repository.tv_list_repository.remote_data_source

import com.arthur.tv_maze.data.remote.api.TvMazeApi
import com.arthur.tv_maze.data.remote.dto.TvListResponseDto
import com.arthur.tv_maze.data.repository.tv_list_repository.repositorys.TvListRemoteDataSource
import com.arthur.tv_maze.utils.ServiceResult
import com.arthur.tv_maze.utils.networkCall

class TvListRetrofitRemoteDataSource(
    private val tvMazeApi: TvMazeApi
) : TvListRemoteDataSource {

    override suspend fun getTodayTvShowList(): ServiceResult<TvListResponseDto> =
        networkCall {
            tvMazeApi.getTodayTvShowList().body()!!
        }

}