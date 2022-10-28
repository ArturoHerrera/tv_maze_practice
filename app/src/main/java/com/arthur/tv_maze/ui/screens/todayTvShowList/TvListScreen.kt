package com.arthur.tv_maze.ui.screens.todayTvShowList

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.arthur.tv_maze.ui.screens.components.SearchBar
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
        scaffoldState = scaffoldState
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.Black),
            verticalArrangement = Arrangement.Top
        ) {
            SearchBar(
                onWriteQuery = {
                    Log.i("testSearch", "query --> $it")
                },
                onSearchClicked = {},
                hideKeyboard = hideKeyboard,
                onFocusClear = { hideKeyboard = false }
            )
            TvShowTodayList(uiState.todayTvShowList)
        }
    }
}