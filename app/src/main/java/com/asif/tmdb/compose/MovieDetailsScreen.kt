package com.asif.tmdb.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.asif.tmdb.data.movieDetails.MovieDetailsWrapped
import com.asif.tmdb.utils.BACKDROP_BASE_URL
import com.asif.tmdb.utils.POSTER_IMAGE_BASE_URL
import com.asif.tmdb.viewmodels.MovieDetailsViewModel

@Composable
fun MovieDetailsScreen(
    title: String,
    id: Int,
    navController: NavController,
    movieDetailsViewModel: MovieDetailsViewModel = hiltViewModel()
) {

    var isApiCalled by rememberSaveable { mutableStateOf(false) }

    if (!isApiCalled) {
        movieDetailsViewModel.getMovieDetails(id)
        isApiCalled = true
    }

    val movieDetails = movieDetailsViewModel.movieDetails.collectAsState(initial = null)

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

            MovieDetailsTopAppBar(id, navController, movieDetails)

            BackDropImage(movieDetails)
        }
    }
}

@Composable
fun BackDropImage(movieDetails: State<MovieDetailsWrapped?>) {
    movieDetails.value?.backdropPath?.let {
        ImageCompose(
            imagePath = BACKDROP_BASE_URL + it,
            height = 300,
            cornerRadius = 0,
            alphaVal = 0.2f
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsTopAppBar(
    movieId: Int,
    navController: NavController,
    movieDetails: State<MovieDetailsWrapped?>
) {

    val scrollBehavior =
        TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "back button",
                            tint = Color.White
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black)
                .padding(it)
                .verticalScroll(rememberScrollState())
        ) {
            BodyContent(movieDetails, movieId)
        }
    }
}

@Composable
fun BodyContent(movieDetails: State<MovieDetailsWrapped?>, movieId: Int) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 10.dp)
    ) {

        movieDetails.value?.let { details ->
            Text(
                text = details.title,
                fontSize = 22.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 50.dp)
            )


            Row(modifier = Modifier.padding(top = 10.dp)) {

                PosterImageDetailsPage(
                    imagePath = POSTER_IMAGE_BASE_URL + details.posterPath,
                    height = 200,
                    cornerRadius = 0,
                    width = 140,
                    modifier = Modifier
                        .size(140.dp, 200.dp)
                        .shadow(
                            shape = RoundedCornerShape(10.dp),
                            elevation = 20.dp,
                            spotColor = Color.White,
                            ambientColor = Color.White
                        )
                )

                Column(modifier = Modifier.padding(start = 10.dp)) {
                    Text(
                        text = details.genreNReleaseDate,
                        fontSize = 15.sp,
                        color = Color.White,
                        maxLines = 2,
                        fontWeight = FontWeight.SemiBold
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .border(
                                    width = 2.dp,
                                    shape = CircleShape,
                                    color = Color.Green,
                                )
                                .size(32.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = details.voteAverage,
                                fontSize = 14.sp,
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        Box(
                            modifier = Modifier
                                .padding(start = 10.dp)
                                .border(
                                    width = 2.dp,
                                    shape = androidx.compose.ui.graphics.RectangleShape,
                                    color = Color.Green,
                                )
                                .size(32.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = details.originalLanguage,
                                fontSize = 14.sp,
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                    Text(
                        text = "Status",
                        fontSize = 12.sp,
                        color = Color.White,
                        modifier = Modifier.padding(top = 10.dp),
                        fontWeight = FontWeight.Light
                    )

                    Text(
                        text = details.status,
                        fontSize = 12.sp,
                        color = Color.Green,
                        fontWeight = FontWeight.Light
                    )

                    Text(
                        text = "Revenue",
                        fontSize = 12.sp,
                        color = Color.White,
                        modifier = Modifier.padding(top = 10.dp),
                        fontWeight = FontWeight.Light
                    )

                    Text(
                        text = details.revenue,
                        fontSize = 12.sp,
                        color = Color.Green,
                        fontWeight = FontWeight.Light
                    )
                }
            }

            Text(
                text = "Original title",
                fontSize = 13.sp,
                color = Color.White,
                fontWeight = FontWeight.Light,
                modifier = Modifier
                    .padding(top = 10.dp)
                    .alpha(0.8f)
            )

            Text(
                text = details.originalTitle,
                fontSize = 15.sp,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 10.dp)
            )


            Text(
                text = "Overview",
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 10.dp)
            )

            Text(
                text = details.tagline,
                fontSize = 12.sp,
                color = Color.White,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .padding(top = 15.dp)
                    .alpha(0.7f)
            )

            Text(
                text = details.overview,
                fontSize = 12.sp,
                color = Color.White,
                fontWeight = FontWeight.Light,
                modifier = Modifier
                    .padding(top = 15.dp)
                    .alpha(0.9f),
                lineHeight = 14.sp
            )

            TrailersList(movieId)

        }
    }

}

@Composable
fun TrailersList(movieId: Int) {

    Row {
        Text(
            text = "Trailers",
            fontSize = 16.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 10.dp)
        )

        Text(
            text = "(Coming soon)",
            fontSize = 14.sp,
            color = Color.White,
            fontWeight = FontWeight.Light,
            modifier = Modifier.padding(10.dp)
        )
    }


    LazyRow(modifier = Modifier.padding(top = 10.dp)) {
        items(6) {
            TrailerView()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MovieDetailsScreenPreview() {
    MovieDetailsScreen("Saw X", 1, rememberNavController())
}