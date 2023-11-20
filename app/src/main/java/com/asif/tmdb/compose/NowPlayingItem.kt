package com.asif.tmdb.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.asif.tmdb.R

@Composable
fun NowPlayingItem() {
    Box(
        modifier = Modifier
            .width(320.dp)
            .padding(end = 10.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.loki2),
            contentDescription = null,
            modifier = Modifier
                .height(198.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop,
            alpha = 0.9f
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .align(Alignment.BottomStart)
        ) {
            Image(
                painter = painterResource(id = R.drawable.loki3),
                contentDescription = null,
                modifier = Modifier
                    .height(110.dp)
                    .width(80.dp)
                    .shadow(
                        elevation = 15.dp,
                        spotColor = Color.Black,
                        ambientColor = Color.Black
                    )
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Bottom)
                    .padding(horizontal = 10.dp)
                    .shadow(elevation = 50.dp, spotColor = Color.Black)
            ) {

                Text(
                    text = "Loki - Season 2",
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "The story of Tim Bellard, a former US government agent, who quites his job in order to devote his life to rescuing child from global traffickers",
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = Color.White,
                    fontSize = 11.sp,
                    style = TextStyle(lineHeight = 11.sp),
                    maxLines = 3
                )
            }
        }
    }
}

@Preview
@Composable
fun NowPlayingItemPreview() {
    NowPlayingItem()
}