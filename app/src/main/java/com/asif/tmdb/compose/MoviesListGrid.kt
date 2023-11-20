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

@Composable
fun MoviesListGrid() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        MovieListAppBar("Popular Movies")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListAppBar(title: String) {
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
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = Color.White)
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
            GridList()
        }
    }

}

@Composable
fun GridList() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(10.dp)
    ) {
        items(7) { movie ->
            GridMovieItem()
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun MoviesListGridPreview() {
    MoviesListGrid()
}