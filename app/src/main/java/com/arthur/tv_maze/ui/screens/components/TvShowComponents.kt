package com.arthur.tv_maze.ui.screens.components

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.graphics.Color
import com.arthur.tv_maze.R
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.arthur.tv_maze.data.model.TvShowSimple
import com.arthur.tv_maze.utils.StringUtils
import com.google.gson.Gson
import kotlin.random.Random

@Composable
fun TvShowTodayList(
    todayTvShowList: List<TvShowSimple>,
    isPortraitMode: Boolean = true,
    isFindertMode: Boolean = false,
    onMediaClick: (Long) -> Unit
) {
    if (todayTvShowList.isEmpty()) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            NoRegisters()
        }
    } else {

        when (isFindertMode) {
            false -> {
                if (isPortraitMode) {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(8.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(todayTvShowList) { tvShow ->
                            TvShowListItemPortraitMode(
                                tvShow = tvShow,
                                onMediaClick = { onMediaClick(it) }
                            )
                        }
                    }
                } else {
                    val listState = rememberLazyGridState()
                    LazyVerticalGrid(
                        state = listState,
                        columns = GridCells.Fixed(5),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(todayTvShowList) { tvShow ->
                            TvShowListItemLandscapeMode(
                                tvShow = tvShow,
                                onMediaClick = { onMediaClick(it) }
                            )
                        }
                    }

                }
            }
            true -> {
                if (isPortraitMode) {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(8.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(todayTvShowList) { tvShow ->
                            TvShowListFinderItemPortraitMode(
                                tvShow = tvShow,
                                onMediaClick = { onMediaClick(it) }
                            )
                        }
                    }
                } else {
                    val listState = rememberLazyGridState()
                    LazyVerticalGrid(
                        state = listState,
                        columns = GridCells.Fixed(5),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(todayTvShowList) { tvShow ->
                            TvShowListFinderItemLandscapeMode(
                                tvShow = tvShow,
                                onMediaClick = { onMediaClick(it) }
                            )
                        }
                    }

                }
            }
        }


    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TvShowListItemPortraitMode(tvShow: TvShowSimple, onMediaClick: (Long) -> Unit) {
    Card(
        elevation = 5.dp,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = Color.Black,
        onClick = { onMediaClick(tvShow.id ?: -1) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Column(
                modifier = Modifier
                    .weight(2.0f)
                    .height(150.dp)
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxHeight(),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(tvShow.posterUrl)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.ic_no_image),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
            }
            Column(
                modifier = Modifier
                    .weight(5.0f)
                    .padding(8.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    text = tvShow.name ?: "No disponible",
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 22.sp,
                    fontSize = 22.sp,
                    color = Color.White
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    text = tvShow.networkName ?: "No disponible",
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Light,
                    lineHeight = 18.sp,
                    fontSize = 18.sp,
                    color = Color.White
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "${tvShow.airDate}  |  ${tvShow.airTime}",
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Light,
                    lineHeight = 18.sp,
                    fontSize = 18.sp,
                    color = Color.White
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TvShowListItemLandscapeMode(tvShow: TvShowSimple, onMediaClick: (Long) -> Unit) {
    Card(
        backgroundColor = Color(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)),
        modifier = Modifier
            .size(130.dp, 210.dp)
            .padding(8.dp),
        elevation = 8.dp,
        onClick = { onMediaClick(tvShow.id ?: -1) }
    ) {
        Column() {
            Box() {
                AsyncImage(
                    modifier = Modifier.fillMaxHeight(),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(tvShow.posterUrl)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.ic_no_image),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter),
                    color = Color.Black.copy(alpha = 0.7f)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = tvShow.name ?: "No disponible",
                            fontSize = 12.sp,
                            style = MaterialTheme.typography.h6,
                            color = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 0.dp, end = 0.dp, top = 8.dp, bottom = 8.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TvShowListFinderItemPortraitMode(tvShow: TvShowSimple, onMediaClick: (Long) -> Unit) {
    Card(
        elevation = 5.dp,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = Color.Black,
        onClick = { onMediaClick(tvShow.id ?: -1) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Column(
                modifier = Modifier
                    .weight(2.0f)
                    .height(150.dp)
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxHeight(),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(tvShow.posterUrl)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.ic_no_image),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
            }
            Column(
                modifier = Modifier
                    .weight(5.0f)
                    .padding(8.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    text = tvShow.name ?: "No disponible",
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 22.sp,
                    fontSize = 22.sp,
                    color = Color.White
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    text = tvShow.networkName ?: "No disponible",
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Light,
                    lineHeight = 18.sp,
                    fontSize = 18.sp,
                    color = Color.White
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${tvShow.time?.ifBlank { "--" } ?: "--"}  |  ",
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.Light,
                        lineHeight = 18.sp,
                        fontSize = 18.sp,
                        color = Color.White
                    )
                    Text(
                        text = StringUtils.turnStringListToUniqueWord(tvShow.days ?: emptyList()),
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.ExtraBold,
                        lineHeight = 18.sp,
                        fontSize = 15.sp,
                        color = Color.White
                    )
                }

            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TvShowListFinderItemLandscapeMode(tvShow: TvShowSimple, onMediaClick: (Long) -> Unit) {
    Card(
        backgroundColor = Color(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)),
        modifier = Modifier
            .size(130.dp, 210.dp)
            .padding(8.dp),
        elevation = 8.dp,
        onClick = { onMediaClick(tvShow.id ?: -1) }
    ) {
        Column() {
            Box() {
                AsyncImage(
                    modifier = Modifier.fillMaxHeight(),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(tvShow.posterUrl)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.ic_no_image),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter),
                    color = Color.Black.copy(alpha = 0.7f)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = tvShow.name ?: "No disponible",
                            fontSize = 12.sp,
                            style = MaterialTheme.typography.h6,
                            color = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 0.dp, end = 0.dp, top = 8.dp, bottom = 8.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

        }
    }
}
