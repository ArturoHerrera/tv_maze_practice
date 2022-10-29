package com.arthur.tv_maze.data.repository.tv_list_repository.repositorys

import com.arthur.tv_maze.data.model.TodayTvShowList
import com.arthur.tv_maze.data.model.TvShowSimple
import com.arthur.tv_maze.data.remote.dto.TvResponseDto
import com.arthur.tv_maze.utils.ServiceResult
import com.arthur.tv_maze.utils.getDto
import com.arthur.tv_maze.utils.getMessage
import com.arthur.tv_maze.utils.succeeded
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class TvListRepository(
    private val tvListRemoteDS: TvListRemoteDataSource
) : TvShowListTasks {

    override suspend fun getTvMazeShowList(): Flow<TodayTvShowList> = flow {
        emit(tvListRemoteDS.getTodayTvShowList())
    }.map { result ->
        if (result.succeeded) {
            val mList = result.getDto().map { mTvResponseDto ->
                TvShowSimple.Builder()
                    .setId(mTvResponseDto?.show?.id)
                    .setName(mTvResponseDto?.show?.name)
                    .setNetworkName(mTvResponseDto?.show?.network?.name)
                    .setAirDate(airDate = mTvResponseDto?.airdate)
                    .setAirTime(airTime = mTvResponseDto?.airtime)
                    .setDays(days = mTvResponseDto?.show?.schedule?.days)
                    .setTime(time = mTvResponseDto?.show?.schedule?.time)
                    .setPosterUrl(mTvResponseDto?.show?.image?.medium)
                    .build()
            }
            TodayTvShowList(TvShowSimpleList = mList, errorMessage = null)
        } else {
            TodayTvShowList(TvShowSimpleList = emptyList(), errorMessage = result.getMessage())
        }
    }
        .catch { e -> e.printStackTrace() }
        .flowOn(Dispatchers.IO)
}

interface TvListRemoteDataSource {

    suspend fun getTodayTvShowList(): ServiceResult<List<TvResponseDto>>

}