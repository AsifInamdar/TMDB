package com.asif.tmdb.compose

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TrailerView() {
    Box(
        modifier = Modifier
            .size(260.dp, 130.dp)
            .padding(end = 10.dp)
            .border(width = 1.dp, color = Color.Red, shape = RoundedCornerShape(5))
    ) {
        ImageCompose(
            modifier = Modifier
                .padding(2.dp),
            height = 150.dp,
            imagePath = "", cornerRadius = 5
        )

        Canvas(modifier = Modifier
            .size(45.dp)
            .align(Alignment.Center)
            .clickable {

            },
            onDraw = {
                drawCircle(color = Color.Black, alpha = 0.4f)
            })

        Icon(
            imageVector = Icons.Filled.PlayArrow,
            contentDescription = "play button",
            tint = Color.White,
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}


@Preview
@Composable
fun TrailerViewPreview() {
    TrailerView()
}
