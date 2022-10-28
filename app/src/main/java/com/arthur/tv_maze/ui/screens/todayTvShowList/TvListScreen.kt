package com.arthur.tv_maze.ui.screens.todayTvShowList

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.arthur.tv_maze.ui.screens.components.ProgressBar
import com.arthur.tv_maze.ui.screens.components.SearchBar
import com.arthur.tv_maze.ui.screens.components.TopBarComponent
import com.arthur.tv_maze.ui.screens.components.TvShowTodayList
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@Composable
fun TvListScreen(
    navigateToView: () -> Unit,
    viewModel: TvListViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()

    val uiState by viewModel.uiState.collectAsState()

    var hideKeyboard by remember { mutableStateOf(false) }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            if (uiState.activeSearch) {
                viewModel.clearTvShowList()
                SearchBar(
                    hideKeyboard = hideKeyboard,
                    onFocusClear = { hideKeyboard = false },
                    onBack = {
                        viewModel.setActiveSearchState(false)
                        viewModel.getTvShowList()
                    },
                    onWriteQuery = {
                        Log.i("testSearch", "query --> $it")
                    }
                )
            } else {
                TopBarComponent(onSearchClicked = {
                    viewModel.setActiveSearchState(true)
                })
            }
        }
    ) { paddingValues ->

        Box {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color.Black.copy(alpha = 0.75f)),
                verticalArrangement = Arrangement.Top
            ) {
                TvShowTodayList(uiState.todayTvShowList)
            }
        }
        ProgressBar(state = uiState.loading)
    }
}