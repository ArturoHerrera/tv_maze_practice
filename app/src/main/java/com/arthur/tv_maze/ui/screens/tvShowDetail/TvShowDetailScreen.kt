package com.arthur.tv_maze.ui.screens.tvShowDetail

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.arthur.tv_maze.ui.screens.components.*
import com.arthur.tv_maze.utils.IntentUtils.openExternalUrl

@Composable
fun TvShowDetailScreen(
    navigateToView: () -> Unit,
    viewModel: TvShowDetailViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val uiState by viewModel.uiState.collectAsState()

    val context = LocalContext.current

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
                    if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT) {
                        DetailPortraitHeader(it, onVisitSiteClicked = { url ->
                            openExternalUrl(
                                externalUrl = url,
                                context = context
                            )
                        })
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