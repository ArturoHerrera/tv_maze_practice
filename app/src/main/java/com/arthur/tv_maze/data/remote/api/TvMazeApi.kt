package com.arthur.tv_maze.data.remote.api

import com.arthur.tv_maze.data.remote.dto.TvResponseDto
import com.arthur.tv_maze.data.remote.dto.TvShowDetailResponseDto
import com.arthur.tv_maze.utils.DateUtils
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface TvMazeApi {

    @GET("/schedule")
    suspend fun getTodayTvShowList(
        @Query("country") country: String = "US",
        @Query("date") date: String = DateUtils.getCurrentDate()
    ): Response<List<TvResponseDto>>

    @GET("/shows/{tvShowId}")
    suspend fun getTvShowDetail(
        @Path("tvShowId") tvShowId: Long = 2831
    ): Response<TvShowDetailResponseDto>

}