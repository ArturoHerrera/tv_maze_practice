package com.arthur.tv_maze.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.arthur.tv_maze.ui.screens.todayTvShowList.TvListScreen
import com.arthur.tv_maze.ui.screens.tvShowDetail.TvShowDetailScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi

object Destinations {
    const val TV_SHOW_LIST_SCREEN = "tv_show_list_screen"
    const val DETAIL_SCREEN = "tv_show_detail"
}

object NavArgs {
    const val TV_SHOW_ID = "tv_show_id"
}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@Composable
fun TvMazeNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Destinations.TV_SHOW_LIST_SCREEN
) {
    val actions = remember(navController) { MainActions(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            route = Destinations.TV_SHOW_LIST_SCREEN
        ) {
            TvListScreen(
                navigateToTvDetail = actions.navigateToTvDetail,
            )
        }
        composable(
            route = Destinations.DETAIL_SCREEN + "/{${NavArgs.TV_SHOW_ID}}",
            arguments = listOf(navArgument(NavArgs.TV_SHOW_ID) { type = NavType.LongType })
        ) {
            TvShowDetailScreen(
                navigateToView = actions.upPress
            )
        }
    }
}

class MainActions(navController: NavHostController) {
    val navigateToTvDetail: (Long) -> Unit = { tvShowId ->
        navController.navigate(Destinations.DETAIL_SCREEN + "/$tvShowId")
    }
    val upPress: () -> Unit = {
        navController.navigateUp()
    }
}