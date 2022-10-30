package com.arthur.tv_maze.ui.screens.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.arthur.tv_maze.R
import com.arthur.tv_maze.data.model.TvShowDetailSimple
import com.arthur.tv_maze.ui.theme.MazeGreen
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.gowtham.ratingbar.RatingBarStyle
import com.gowtham.ratingbar.StepSize
import de.charlex.compose.HtmlText

@Composable
fun DetailPortraitHeader(
    tvShowDetail: TvShowDetailSimple
) {
    var rating: Float by remember { mutableStateOf(tvShowDetail.rating) }

    Column(
        modifier = Modifier.background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(Modifier.height(550.dp)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(tvShowDetail.coverBigUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_no_image),
                contentDescription = null,
                alignment = Alignment.BottomCenter,
                contentScale = ContentScale.FillHeight
            )
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black,
                            )
                        )
                    )
            )
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp, start = 8.dp, end = 8.dp),
            text = tvShowDetail.name ?: "--",
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold,
            lineHeight = 18.sp,
            maxLines = 2,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            color = Color.White
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp),
            text = tvShowDetail.networkName ?: "--",
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Light,
            lineHeight = 18.sp,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            color = Color.White
        )
        RatingBar(
            modifier = Modifier.padding(8.dp),
            value = rating,
            config = RatingBarConfig()
                .activeColor(MazeGreen)
                .hideInactiveStars(true)
                .inactiveColor(Color.LightGray)
                .inactiveBorderColor(Color.DarkGray)
                .stepSize(StepSize.HALF)
                .numStars(10)
                .isIndicator(true)
                .size(18.dp)
                .padding(6.dp)
                .style(RatingBarStyle.HighLighted)
                .style(RatingBarStyle.HighLighted),
            onValueChange = {
                rating = it
            },
            onRatingChanged = {
                Log.d("TAG", "onRatingChanged: $it")
            }
        )
    }
}

@Composable
fun DetailPortraitBody(
    tvShowDetail: TvShowDetailSimple
) {
    Column(
        modifier = Modifier
            .background(Color.Black)
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Sinopsis:",
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold,
            lineHeight = 18.sp,
            fontSize = 18.sp,
            textAlign = TextAlign.Start,
            color = Color.White
        )
        HtmlText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            text = tvShowDetail.summary.ifBlank { "--" },
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Light,
            lineHeight = 18.sp,
            fontSize = 18.sp,
            textAlign = TextAlign.Start,
            color = Color.White
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Géneros:",
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold,
            lineHeight = 18.sp,
            fontSize = 18.sp,
            textAlign = TextAlign.Start,
            color = Color.White
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            text = tvShowDetail.genres.ifBlank { "--" },
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Light,
            lineHeight = 18.sp,
            fontSize = 18.sp,
            textAlign = TextAlign.Start,
            color = Color.White
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Horario:",
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold,
            lineHeight = 18.sp,
            fontSize = 18.sp,
            textAlign = TextAlign.Start,
            color = Color.White
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            text = tvShowDetail.scheduleTimeDays.ifBlank { "--" },
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Light,
            lineHeight = 18.sp,
            fontSize = 18.sp,
            textAlign = TextAlign.Start,
            color = Color.White
        )
    }
}
