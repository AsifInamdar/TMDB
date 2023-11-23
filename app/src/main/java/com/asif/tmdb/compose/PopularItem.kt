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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.asif.tmdb.data.MovieListDetail
import com.asif.tmdb.viewmodels.MainViewModel

const val IMAGE_BASE_URL = "https://www.themoviedb.org/t/p/w220_and_h330_face"

@Composable
fun PopularItem(modifier: Modifier = Modifier, movie: MovieListDetail, clickFunction : ()-> Unit) {
    Column(
        modifier = modifier
            .width(128.dp)
            .padding(horizontal = 5.dp)
            .clickable {
                clickFunction()
            }
    ) {

        ImageCompose(imagePath = IMAGE_BASE_URL + movie.posterPath, height = 168)

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
    val viewModel: MainViewModel = hiltViewModel()
    PopularItem(Modifier.background(color = Color.Black), viewModel.getStaticMovieObject(), {})
}