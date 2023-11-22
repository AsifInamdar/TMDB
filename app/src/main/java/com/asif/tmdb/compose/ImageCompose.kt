package com.asif.tmdb.compose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.asif.tmdb.R

@Composable
fun ImageCompose(
    imagePath: String,
    height: Int,
    contentScale: ContentScale = ContentScale.Crop
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imagePath)
            .build(),
        contentDescription = null,
        placeholder = debugPlaceholder(R.drawable.loki),
        error = ColorPainter(Color.Red),
        modifier = Modifier
            .height(height.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp)),
        contentScale = contentScale
    )
}

@Composable
fun debugPlaceholder(@DrawableRes debugPreview: Int) =
    if (LocalInspectionMode.current) {
        painterResource(id = debugPreview)
    } else {
        null
    }

@Preview
@Composable
fun ImageComposePreview() {
    ImageCompose(
        "https://www.themoviedb.org/t/p/w220_and_h330_face/1XS1oqL89opfnbLl8WnZY1O1uJx.jpg",
        168
    )
}