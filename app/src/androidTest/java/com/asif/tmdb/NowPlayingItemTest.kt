package com.asif.tmdb

import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.asif.tmdb.compose.NowPlayingItem
import com.asif.tmdb.utils.getStaticMovieObject
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NowPlayingItemTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun nowPlayingItem() {
        composeTestRule.activity.setContent {
            NowPlayingItem(movie = getStaticMovieObject()) {

            }
        }

        composeTestRule.onNodeWithTag("NowPlayingItem").performClick()
        composeTestRule.onNodeWithText("Game Of Thrones").assertExists()
    }
}