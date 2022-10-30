package com.arthur.tv_maze.ui.screens.tvShowDetail

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.hilt.navigation.compose.hiltViewModel
import com.arthur.tv_maze.ui.screens.components.*

@Composable
fun TvShowDetailScreen(
    navigateToView: () -> Unit,
    viewModel: TvShowDetailViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        scaffoldState = scaffoldState
    ) { paddingValues ->
        Box {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color.Black)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Top
            ) {
                uiState.tvShowDetail?.let {
                    if(LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT){
                        DetailPortraitHeader(it)
                        DetailPortraitBody(it)
                        DetailPortrairCasting(uiState.tvShowCast)
                    } else {

                    }
                }
            }
        }
        ProgressBar(state = uiState.loading)
        uiState.errorMsg?.let { safeErrorMsg ->
            ErrorAlert(
                errorMsg = safeErrorMsg,
                onDismiss = { viewModel.clearErrorMsg() }
            )
        }
    }
}