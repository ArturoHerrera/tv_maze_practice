package com.arthur.tv_maze.data.repository.tv_detail_repository.repositorys

import com.arthur.tv_maze.data.model.TvShowActor
import com.arthur.tv_maze.data.model.TvShowDetail
import kotlinx.coroutines.flow.Flow

interface TvShowDetailTasks {

    /*
    *   Al tener nuestro manifiesto de tareas,
    *   podemos implementar esta interface en un
    *   repositorio de prueba, para facilitar y promover
    *   el testing.
    */

    suspend fun getTvShowDetail( tvShowId: Long ): Flow<TvShowDetail>

    suspend fun getTvShowActorList( tvShowId: Long ): Flow<TvShowActor>

}