package com.arthur.tv_maze.data.repository.tv_detail_repository.repositorys

import com.arthur.tv_maze.data.model.TvShowActor
import com.arthur.tv_maze.data.model.TvShowActorSimple
import com.arthur.tv_maze.data.model.TvShowDetail
import com.arthur.tv_maze.data.model.TvShowDetailSimple
import com.arthur.tv_maze.data.remote.dto.ActorResponseDto
import com.arthur.tv_maze.data.remote.dto.TvShowDetailResponseDto
import com.arthur.tv_maze.utils.ServiceResult
import com.arthur.tv_maze.utils.getDto
import com.arthur.tv_maze.utils.getMessage
import com.arthur.tv_maze.utils.succeeded
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class TvDetailRepository(
    private val tvDetailRemoteDS: TvDetailRemoteDataSource
) : TvShowDetailTasks {

    override suspend fun getTvShowDetail(): Flow<TvShowDetail> = flow {
        emit(tvDetailRemoteDS.getTvShowDetail())
    }.map { result ->
        if (result.succeeded) {
            val mTvShowDetailDto = result.getDto()
            val mTvShowDetailSimple = TvShowDetailSimple.Builder()
                .setName(mTvShowDetailDto.name)
                .setNetworkName(mTvShowDetailDto.network?.name)
                .setCoverMiniUrl(mTvShowDetailDto.image?.medium)
                .setCoverBigUrl(mTvShowDetailDto.image?.original)
                .setRating(mTvShowDetailDto.rating?.average)
                .setOfficialSiteUrl(mTvShowDetailDto.url)
                .setSummary(mTvShowDetailDto.summary)
                .setGenres(mTvShowDetailDto.genres)
                .setScheduleTimeDays(
                    time = mTvShowDetailDto.schedule?.time,
                    days = mTvShowDetailDto.schedule?.days ?: listOf("--")
                )
                .build()
            TvShowDetail(
                errorMessage = null,
                tvShowDetail = mTvShowDetailSimple
            )
        } else {
            TvShowDetail(
                errorMessage = result.getMessage(),
                tvShowDetail = null
            )
        }
    }.catch { e -> e.printStackTrace() }
        .flowOn(Dispatchers.IO)

    override suspend fun getTvShowActorList(): Flow<TvShowActor> = flow {
        emit(tvDetailRemoteDS.getTvShowCast())
    }.map { result ->
        if (result.succeeded) {
            val mTvShowActorSimple = result.getDto().map { mActorResponseDto ->
                TvShowActorSimple.Builder()
                    .setActorName(mActorResponseDto.person?.name)
                    .setActorPhotoUrl(mActorResponseDto.character?.image?.medium)
                    .build()
            }
            TvShowActor(
                errorMessage = null,
                tvShowActorList = mTvShowActorSimple
            )
        } else {
            TvShowActor(
                errorMessage = result.getMessage(),
                tvShowActorList = emptyList()
            )
        }
    }.catch { e -> e.printStackTrace() }
        .flowOn(Dispatchers.IO)
}

interface TvDetailRemoteDataSource {
    suspend fun getTvShowDetail(): ServiceResult<TvShowDetailResponseDto>
    suspend fun getTvShowCast(): ServiceResult<List<ActorResponseDto>>
}