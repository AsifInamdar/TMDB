package com.asif.tmdb.compose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
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
    modifier: Modifier = Modifier,
    imagePath: String,
    height: Int,
    contentScale: ContentScale = ContentScale.Crop,
    cornerRadius: Int = 10,
    alphaVal: Float = 1f
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imagePath)
            .build(),
        contentDescription = null,
        placeholder = debugPlaceholder(R.drawable.loki),
        error = ColorPainter(Color.White),
        modifier = modifier
            .height(height.dp)
            .fillMaxWidth()
            .alpha(alphaVal)
            .clip(RoundedCornerShape(cornerRadius.dp)),
        contentScale = contentScale
    )
}

@Composable
fun PosterImageDetailsPage(
    modifier: Modifier = Modifier,
    imagePath: String,
    height: Int,
    contentScale: ContentScale = ContentScale.Crop,
    cornerRadius: Int = 10,
    alphaVal: Float = 1f,
    width: Int
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imagePath)
            .build(),
        contentDescription = null,
        placeholder = debugPlaceholder(R.drawable.loki),
        error = ColorPainter(Color.Red),
        modifier = modifier
            .height(height.dp)
            .width(width.dp)
            .alpha(alphaVal)
            .clip(RoundedCornerShape(cornerRadius.dp)),
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
fun PosterImageDetailsPagePreview() {

    PosterImageDetailsPage(
        imagePath = "https://www.themoviedb.org/t/p/w220_and_h330_face/1XS1oqL89opfnbLl8WnZY1O1uJx.jpg",
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
}

@Preview
@Composable
fun ImageComposePreview() {
    ImageCompose(
        modifier = Modifier,
        imagePath = "https://www.themoviedb.org/t/p/w220_and_h330_face/1XS1oqL89opfnbLl8WnZY1O1uJx.jpg",
        height = 168
    )
}