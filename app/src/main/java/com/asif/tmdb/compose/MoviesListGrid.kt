package com.asif.tmdb.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.asif.tmdb.viewmodels.MainViewModel

@Composable
fun MoviesListGrid(
    title: String,
    mainViewModel: MainViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {

    mainViewModel.setMovieListType(title)

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        MovieListAppBar(title = title, navController = navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListAppBar(title: String, navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = title)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
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
        ) {
            GridList(navController = navController)
        }
    }

}

@Composable
fun GridList(mainViewModel: MainViewModel = hiltViewModel(), navController: NavHostController) {

    val movies = mainViewModel.movieListPaginated.collectAsLazyPagingItems()

    LazyVerticalGrid(
        columns = GridCells.Adaptive(148.dp),
        modifier = Modifier.padding(horizontal = 10.dp)
    ) {
        items(movies.itemCount) { index ->
            movies[index]?.let { movie ->
                GridMovieItem(movie) {
                    navController.navigate(Screens.MovieDetailsScreen.createRoute(movie.title, movie.id))
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun MoviesListGridPreview() {
    MoviesListGrid(title = "Popular Movies")
}