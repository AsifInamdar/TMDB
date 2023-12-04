package com.asif.tmdb.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.asif.tmdb.data.movieList.MovieListDetail
import com.asif.tmdb.utils.POSTER_IMAGE_BASE_URL
import com.asif.tmdb.viewmodels.MainViewModel

@Composable
fun GridMovieItem(movie: MovieListDetail) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {

        ImageCompose(imagePath = POSTER_IMAGE_BASE_URL + movie.posterPath, height = 200)

        Text(
            text = movie.originalTitle,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .align(Alignment.CenterHorizontally),
            color = Color.White,
            fontSize = 11.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun GridMovieItemPreview() {
    val viewModel: MainViewModel = hiltViewModel()
    GridMovieItem(viewModel.getStaticMovieObject())
}