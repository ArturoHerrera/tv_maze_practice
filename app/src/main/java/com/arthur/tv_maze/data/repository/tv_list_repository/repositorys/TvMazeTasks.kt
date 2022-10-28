package com.arthur.tv_maze.data.repository.tv_list_repository.repositorys

import com.arthur.tv_maze.data.model.TodayTvShowList
import kotlinx.coroutines.flow.Flow

interface TvMazeTasks {

    /*
    *   Al tener nuestro manifiesto de tareas,
    *   podemos implementar esta interface en un
    *   repositorio de prueba, para facilitar y promover
    *   el testing.
    */

    suspend fun getTvMazeShowList(): Flow<TodayTvShowList>

}