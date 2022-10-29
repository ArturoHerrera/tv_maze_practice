package com.arthur.tv_maze.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arthur.tv_maze.ui.screens.todayTvShowList.TvListScreen
import com.arthur.tv_maze.ui.screens.tvShowDetail.TvShowDetailScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi

object Destinations {
    const val HOME_SCREEN = "tvShowList"
    const val DETAIL_SCREEN = "tvShowDetail"
}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@Composable
fun TvMazeNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Destinations.HOME_SCREEN
) {
    val actions = remember(navController) { MainActions(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Destinations.HOME_SCREEN) {
            TvListScreen(
                navigateToTvDetail = actions.navigateToTvDetail,
            )
        }
        composable(Destinations.DETAIL_SCREEN){
            TvShowDetailScreen(
                navigateToView = actions.upPress
            )
        }
    }
}

class MainActions(navController: NavHostController) {
    val navigateToTvDetail: () -> Unit = {
        navController.navigate(Destinations.DETAIL_SCREEN)
    }
    val upPress: () -> Unit = {
        navController.navigateUp()
    }
}