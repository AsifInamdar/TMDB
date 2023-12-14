package com.asif.tmdb.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.asif.tmdb.data.movieList.MovieListDetail
import com.asif.tmdb.utils.POSTER_IMAGE_BASE_URL
import com.asif.tmdb.utils.getStaticMovieObject

@Composable
fun PopularItem(modifier: Modifier = Modifier,
                movie: MovieListDetail,
                clickFunction: () -> Unit) {
    Column(
        modifier = modifier
            .width(128.dp)
            .padding(horizontal = 5.dp)
            .clickable {
                clickFunction()
            }
            .testTag("PopularItem")
    ) {

        ImageCompose(imagePath = POSTER_IMAGE_BASE_URL + movie.posterPath, height = 168.dp)

        Text(
            text = movie.originalTitle,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .align(Alignment.CenterHorizontally),
            color = Color.White,
            fontSize = 11.sp,
            textAlign = TextAlign.Center,
            lineHeight = 11.sp,
            minLines = 3
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PopularItemPreview() {
    PopularItem(Modifier.background(color = Color.Black), getStaticMovieObject(), {})
}