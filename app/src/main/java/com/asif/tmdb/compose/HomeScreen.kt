package com.asif.tmdb.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.asif.tmdb.R
import com.asif.tmdb.utils.showToast
import com.asif.tmdb.viewmodels.MainViewModel

@Composable
fun HomeMain(
    onMovieClick: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        AppBar(onMovieClick)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(onMovieClick: (String) -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.home))
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Menu, contentDescription = null, tint = Color.White)
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Search, contentDescription = null, tint = Color.White)
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(color = Color.Black)
                .verticalScroll(rememberScrollState())
        ) {

            PopularList(onMovieClick = onMovieClick)

            TopRatedList(onMovieClick = onMovieClick)

            NowPlayingList()

            UpcomingList(onMovieClick = onMovieClick)
        }
    }

}

@Composable
fun PopularList(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
    onMovieClick: (String) -> Unit
) {

    var isApiCalled by rememberSaveable { mutableStateOf(false) }

    if (!isApiCalled) {
        viewModel.getPopularMovies()
        isApiCalled = true
    }

    val moviesList by viewModel.popularMovieList.collectAsState()
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 8.dp)
    ) {

        SectionHeader(title = "Popular", onMovieClick = onMovieClick)

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 5.dp)
        ) {
            items(moviesList) { movie ->
                PopularItem(movie = movie, clickFunction = {
                    showToast(context, movie.originalTitle)
                })
            }
        }
    }
}

@Composable
fun TopRatedList(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
    onMovieClick: (String) -> Unit
) {
    var isApiCalled by rememberSaveable { mutableStateOf(false) }

    if (!isApiCalled) {
        viewModel.getTopRatedList()
        isApiCalled = true
    }

    val moviesList by viewModel.topRatedMovieList.collectAsState()

    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 8.dp)
    ) {

        SectionHeader(title = "Top Rated", onMovieClick = onMovieClick)

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 5.dp)
        ) {
            items(moviesList) { movie ->
                PopularItem(movie = movie, clickFunction = {
                    showToast(context, movie.originalTitle)
                })
            }
        }
    }
}

@Composable
fun NowPlayingList(viewModel: MainViewModel = hiltViewModel()) {

    var isApiCalled by rememberSaveable { mutableStateOf(false) }

    if (!isApiCalled) {
        viewModel.getNowPlayingList()
        isApiCalled = true
    }

    val moviesList by viewModel.nowPlayingMovieList.collectAsState()

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 18.dp)
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Now Playing",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(15.dp))

        LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(moviesList) { movie ->
                NowPlayingItem(movie) {
                    showToast(context, movie.originalTitle)
                }
            }
        }
    }
}


@Composable
fun UpcomingList(
    viewModel: MainViewModel = hiltViewModel(),
    onMovieClick: (String) -> Unit
) {

    var isApiCalled by rememberSaveable { mutableStateOf(false) }

    if (!isApiCalled) {
        viewModel.getUpcomingList()
        isApiCalled = true
    }

    val moviesList by viewModel.upcomingMovieList.collectAsState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp)
    ) {

        SectionHeader(title = "Upcoming", onMovieClick = onMovieClick)

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 5.dp)
        ) {
            items(moviesList) { movie ->
                PopularItem(movie = movie, clickFunction = {
                    showToast(context, movie.originalTitle)
                })
            }
        }
    }
}

@Composable
fun SectionHeader(
    modifier: Modifier = Modifier, title: String,
    onMovieClick: (String) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        if (title != "Popular")
            Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(end = 5.dp)
        ) {
            Text(
                text = title,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .align(Alignment.CenterStart),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.White,
            )

            Button(
                modifier = Modifier
                    .height(25.dp)
                    .padding(end = 10.dp)
                    .align(Alignment.CenterEnd),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Black,
                    containerColor = Color.White
                ),
                onClick = {
                    onMovieClick(title)
                },
                shape = RoundedCornerShape(5.dp),
                contentPadding = PaddingValues(
                    5.dp
                )
            ) {
                Text(text = "All", color = Color.Black, fontSize = 12.sp)
            }
        }

        Spacer(modifier = Modifier.height(15.dp))
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomeMainPreview() {
    HomeMain(onMovieClick = {})
}