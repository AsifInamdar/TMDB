package com.asif.tmdb.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.asif.tmdb.R
import com.asif.tmdb.data.movieList.MovieListDetail
import com.asif.tmdb.utils.BACKDROP_BASE_URL
import com.asif.tmdb.utils.POSTER_IMAGE_BASE_URL
import com.asif.tmdb.viewmodels.MainViewModel


@Composable
fun NowPlayingItem(movie: MovieListDetail, clickFunction: () -> Unit) {

    Box(
        modifier = Modifier
            .width(320.dp)
            .padding(end = 10.dp)
            .clickable { clickFunction() }
    ) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(BACKDROP_BASE_URL + movie.backdropPath)
                .build(),
            contentDescription = "backdrop image",
            placeholder = debugPlaceholder(R.drawable.loki),
            error = painterResource(id = R.drawable.loki2),
            modifier = Modifier
                .height(198.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .align(Alignment.BottomStart)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(POSTER_IMAGE_BASE_URL + movie.posterPath)
                    .build(),
                placeholder = painterResource(id = R.drawable.loki3),
                error = painterResource(id = R.drawable.loki2),
                contentDescription = "poster image",
                modifier = Modifier
                    .height(90.dp)
                    .width(72.dp)
                    .shadow(
                        elevation = 20.dp,
                        spotColor = Color.Black,
                        ambientColor = Color.Black
                    )
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Bottom)
                    .padding(horizontal = 10.dp)
                    .shadow(
                        elevation = 50.dp,
                        spotColor = Color.Black,
                        ambientColor = Color.Black
                    )
            ) {

                Text(
                    text = movie.originalTitle,
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2
                )

                Text(
                    text = movie.overview,
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = Color.White,
                    fontSize = 11.sp,
                    style = TextStyle(lineHeight = 11.sp),
                    maxLines = 3
                )
            }
        }
    }
}

@Preview
@Composable
fun NowPlayingItemPreview() {
    val viewModel: MainViewModel = hiltViewModel()
    NowPlayingItem(viewModel.getStaticMovieObject(), {})
}