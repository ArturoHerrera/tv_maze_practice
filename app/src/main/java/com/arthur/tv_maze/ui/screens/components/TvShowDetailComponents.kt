package com.arthur.tv_maze.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Web
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
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
import com.arthur.tv_maze.data.model.TvShowActorSimple
import com.arthur.tv_maze.data.model.TvShowDetailSimple
import com.arthur.tv_maze.ui.theme.MazeGreen
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.gowtham.ratingbar.RatingBarStyle
import com.gowtham.ratingbar.StepSize
import de.charlex.compose.HtmlText

@Composable
fun DetailPortraitHeader(
    tvShowDetail: TvShowDetailSimple,
    onVisitSiteClicked: (String) -> Unit
) {
    var rating: Float by remember { mutableStateOf((tvShowDetail.rating / 2)) }

    Column(
        modifier = Modifier.background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(Modifier.height(600.dp)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(tvShowDetail.coverBigUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_no_image),
                contentDescription = null,
                alignment = Alignment.BottomCenter,
                contentScale = ContentScale.FillBounds
            )
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Transparent,
                                Color.Black,
                            )
                        )
                    )
            )
            Box(
                modifier = Modifier
                    .matchParentSize()
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
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
                    if (tvShowDetail.networkName != "--") {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 8.dp, end = 8.dp),
                            text = "By " + tvShowDetail.networkName,
                            style = MaterialTheme.typography.body1,
                            fontWeight = FontWeight.Light,
                            lineHeight = 18.sp,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 8.dp)
                            .clickable {
                                if (tvShowDetail.officialSiteUrl.isNotBlank()) {
                                    onVisitSiteClicked(tvShowDetail.officialSiteUrl)
                                }
                            },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(horizontal = 8.dp),
                            text = "Visit site",
                            style = MaterialTheme.typography.body1,
                            fontWeight = FontWeight.Light,
                            lineHeight = 18.sp,
                            fontSize = 12.sp,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )

                        Icon(
                            imageVector = Icons.Filled.Web,
                            contentDescription = "Botón para visitar el sitio del show.",
                            tint = Color.White
                        )
                    }


                    RatingBar(
                        modifier = Modifier.padding(top = 8.dp, bottom = 24.dp),
                        value = (rating),
                        config = RatingBarConfig()
                            .activeColor(MazeGreen)
                            .inactiveColor(Color.White)
                            .inactiveBorderColor(Color.White)
                            .hideInactiveStars(false)
                            .stepSize(StepSize.HALF)
                            .numStars(5)
                            .isIndicator(false)
                            .size(32.dp)
                            .padding(8.dp)
                            .style(RatingBarStyle.HighLighted),
                        onValueChange = {},
                        onRatingChanged = {}
                    )
                }
            }
        }
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
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
            color = Color.White
        )
        HtmlText(
            modifier = Modifier
                .fillMaxWidth(),
            text = tvShowDetail.summary.trim().ifBlank { "--" },
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Light,
            lineHeight = 22.sp,
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
            color = Color.White
        )

        if (tvShowDetail.genres.isNotBlank()) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Géneros:",
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold,
                lineHeight = 20.sp,
                fontSize = 20.sp,
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
                lineHeight = 16.sp,
                fontSize = 16.sp,
                textAlign = TextAlign.Start,
                color = Color.White
            )
        }


        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Horario:",
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold,
            lineHeight = 20.sp,
            fontSize = 20.sp,
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
            lineHeight = 16.sp,
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
            color = Color.White
        )
    }
}

@Composable
fun DetailCasting(
    tvShowCast: List<TvShowActorSimple>
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(tvShowCast) { mTvShowActorSimple ->
            Column(
                modifier = Modifier
                    .height(200.dp)
                    .width(100.dp)
            ) {
                Card(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .width(100.dp)
                        .padding(bottom = 8.dp),
                    backgroundColor = Color.Black
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(mTvShowActorSimple.actorPhotoUrl)
                            .crossfade(true)
                            .build(),
                        placeholder = painterResource(R.drawable.ic_no_image),
                        error = painterResource(R.drawable.ic_no_image),
                        contentDescription = null,
                        alignment = Alignment.Center,
                        contentScale = ContentScale.FillBounds
                    )
                }
                Text(
                    text = mTvShowActorSimple.actorName.ifBlank { "--" },
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Light,
                    lineHeight = 18.sp,
                    fontSize = 14.sp,
                    maxLines = 2,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun DetailLandscape(
    tvShowDetail: TvShowDetailSimple,
    tvShowCast: List<TvShowActorSimple>,
    onVisitSiteClicked: (String) -> Unit
) {
    var rating: Float by remember { mutableStateOf((tvShowDetail.rating / 2)) }
    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1.0f),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Box(
                modifier = Modifier.fillMaxHeight()
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(tvShowDetail.coverBigUrl)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.ic_no_image),
                    contentDescription = null,
                    alignment = Alignment.TopStart,
                    contentScale = ContentScale.FillBounds
                )
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Transparent,
                                    Color.Black,
                                )
                            )
                        )
                )
                Box(
                    modifier = Modifier
                        .matchParentSize()
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (tvShowDetail.genres.isNotBlank()) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "Géneros:",
                                style = MaterialTheme.typography.body1,
                                fontWeight = FontWeight.Bold,
                                lineHeight = 20.sp,
                                fontSize = 20.sp,
                                textAlign = TextAlign.Center,
                                color = Color.White
                            )
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 8.dp),
                                text = tvShowDetail.genres.ifBlank { "--" },
                                style = MaterialTheme.typography.body1,
                                fontWeight = FontWeight.Light,
                                lineHeight = 14.sp,
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center,
                                color = Color.White
                            )
                        }

                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Horario:",
                            style = MaterialTheme.typography.body1,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )
                        Text(
                            modifier = Modifier
                                .padding(bottom = 8.dp).fillMaxWidth(),
                            text = tvShowDetail.scheduleTimeDays.ifBlank { "--" },
                            style = MaterialTheme.typography.body1,
                            fontWeight = FontWeight.Light,
                            lineHeight = 14.sp,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )
                    }
                }

            }


        }

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .weight(2.0f)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth(),
                text = tvShowDetail.name ?: "--",
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold,
                lineHeight = 18.sp,
                maxLines = 2,
                fontSize = 24.sp,
                textAlign = TextAlign.Start,
                color = Color.White
            )
            if (tvShowDetail.networkName != "--") {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "By " + tvShowDetail.networkName,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Light,
                    lineHeight = 18.sp,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start,
                    color = Color.White
                )
            }

            Row(
                modifier = Modifier
                    .padding(top = 8.dp, end = 8.dp)
                    .fillMaxWidth()
                    .clickable {
                        if (tvShowDetail.officialSiteUrl.isNotBlank()) {
                            onVisitSiteClicked(tvShowDetail.officialSiteUrl)
                        }
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    modifier = Modifier
                        .padding(end = 8.dp),
                    text = "Visit site",
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Light,
                    lineHeight = 12.sp,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
                Icon(
                    imageVector = Icons.Filled.Web,
                    contentDescription = "Botón para visitar el sitio del show.",
                    tint = Color.White
                )
            }
            RatingBar(
                modifier = Modifier.padding(top = 8.dp, bottom = 24.dp),
                value = (rating),
                config = RatingBarConfig()
                    .activeColor(MazeGreen)
                    .inactiveColor(Color.White)
                    .inactiveBorderColor(Color.White)
                    .hideInactiveStars(false)
                    .stepSize(StepSize.HALF)
                    .numStars(5)
                    .isIndicator(false)
                    .size(32.dp)
                    .padding(8.dp)
                    .style(RatingBarStyle.HighLighted),
                onValueChange = {},
                onRatingChanged = {}
            )
            Text(
                text = "Sinopsis:",
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold,
                lineHeight = 20.sp,
                fontSize = 20.sp,
                textAlign = TextAlign.Start,
                color = Color.White
            )
            HtmlText(
                text = tvShowDetail.summary.trim().ifBlank { "--" },
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Light,
                lineHeight = 16.sp,
                fontSize = 16.sp,
                textAlign = TextAlign.Start,
                color = Color.White
            )
            DetailCasting(tvShowCast)
        }
    }
}
