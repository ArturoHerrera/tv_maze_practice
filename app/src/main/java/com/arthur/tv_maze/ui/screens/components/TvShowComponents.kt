package com.arthur.tv_maze.ui.screens.components

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.graphics.Color
import com.arthur.tv_maze.R
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.arthur.tv_maze.data.model.TvShowSimple

@Composable
fun TvShowTodayList(todayTvShowList: List<TvShowSimple>) {
    if (todayTvShowList.isEmpty()) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            NoRegisters()
        }
    } else {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(todayTvShowList) { tvShow ->
                TvShowListItem(tvShow)
            }
        }
    }
}

@Composable
fun TvShowListItem(tvShow: TvShowSimple) {
    Card(
        elevation = 5.dp,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = Color.Gray
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