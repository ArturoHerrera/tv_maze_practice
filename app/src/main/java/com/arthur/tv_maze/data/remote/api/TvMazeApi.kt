package com.arthur.tv_maze.data.remote.api

import com.arthur.tv_maze.data.remote.dto.ActorResponseDto
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

    /*
    * Si quisieramos respetar de una mejor forma el principio de responsabilidad unica,
    * lo correcto seria mover los siguientes 2 servicios/endPoints a otra interfaz.
    */

    @GET("/shows/{tvShowId}")
    suspend fun getTvShowDetail(
        @Path("tvShowId") tvShowId: Long = 2831
    ): Response<TvShowDetailResponseDto>

    @GET("/shows/{tvShowId}/cast")
    suspend fun getTvShowCast(
        @Path("tvShowId") tvShowId: Long = 2831
    ): Response<List<ActorResponseDto>>

}