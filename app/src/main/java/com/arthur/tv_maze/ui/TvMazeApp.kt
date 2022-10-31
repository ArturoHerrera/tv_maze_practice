package com.arthur.tv_maze.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.arthur.tv_maze.ui.theme.TvMazeTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalAnimationApi
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@Composable
fun TvMazeApp() {
    TvMazeTheme {
        val navController = rememberNavController()

        Scaffold {
            TvMazeNavGraph(
                navController = navController
            )
        }

    }
}