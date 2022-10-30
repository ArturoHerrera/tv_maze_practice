package com.arthur.tv_maze.ui.screens.todayTvShowList

import android.content.res.Configuration
import android.util.Log
import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.hilt.navigation.compose.hiltViewModel
import com.arthur.tv_maze.ui.screens.components.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@Composable
fun TvListScreen(
    navigateToTvDetail: (Long) -> Unit,
    viewModel: TvListViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val uiState by viewModel.uiState.collectAsState()
    var hideKeyboard by remember { mutableStateOf(false) }

    val dpi = LocalDensity.current.density

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            if (uiState.activeSearch) {
                SearchBar(
                    hideKeyboard = hideKeyboard,
                    onFocusClear = { hideKeyboard = false },
                    onBack = {
                        viewModel.setActiveSearchState(false)
                        viewModel.clearQuery()
                        viewModel.getTvShowList()
                    },
                    onWriteQuery = { query -> viewModel.filterTvShow(query) }
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
                    .background(Color.Black.copy(alpha = 0.90f)),
                verticalArrangement = Arrangement.Top
            ) {
                TvShowTodayList(
                    dpi = dpi,
                    todayTvShowList = uiState.todayTvShowList,
                    finderTvShowList = uiState.finderTvShowList,
                    isFindertMode = uiState.activeSearch,
                    isPortraitMode = LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT,
                    onMediaClick = { tvShowId -> navigateToTvDetail(tvShowId) }
                )
            }
        }
        ProgressBar(state = uiState.loading)
        uiState.errorMessage?.let { safeErrorMsg ->
            ErrorAlert(
                errorMsg = safeErrorMsg,
                onDismiss = { viewModel.clearErrorMsg() }
            )
        }
    }
}
