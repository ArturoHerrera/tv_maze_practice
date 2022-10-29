package com.arthur.tv_maze.data.repository.tv_detail_repository.repositorys

import com.arthur.tv_maze.data.model.TvShowDetailSimple
import com.arthur.tv_maze.data.remote.dto.TvShowDetailResponseDto
import com.arthur.tv_maze.utils.ServiceResult
import kotlinx.coroutines.flow.Flow

class TvDetailRepository(
    private val tvDetailRemoteDS: TvDetailRemoteDataSource
) : TvShowDetailTasks {

    override suspend fun getTvShowDetail(): Flow<TvShowDetailSimple> {
        TODO("Not yet implemented")
    }
}

interface TvDetailRemoteDataSource {

    suspend fun getTvShowDetail(): ServiceResult<TvShowDetailResponseDto>

}