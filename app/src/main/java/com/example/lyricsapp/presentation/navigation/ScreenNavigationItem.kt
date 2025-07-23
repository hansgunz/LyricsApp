package com.example.lyricsapp.presentation.navigation

import com.example.lyricsapp.R
import kotlinx.serialization.Serializable

@Serializable
sealed class ScreenNavigationItem(val title: String, val icon: Int) {

    @Serializable
    object HomeScreenNavigationItem: ScreenNavigationItem( title = "Home", icon = R.drawable.ic_lyrics_home)

    @Serializable
    object FavoritesScreenNavigationItem: ScreenNavigationItem(title = "Favorites", icon = R.drawable.ic_lyrics_heart)

    @Serializable
    data class LyricsScreenNavigationItem(val artist: String, val song: String)
        : ScreenNavigationItem(title = "Lyrics", icon = 0)

    @Serializable
    data class LyricsScreenDetailsNavigationItem(val artist: String, val song: String, val lyrics: String = "")
        : ScreenNavigationItem(title = "Lyrics Details", icon = 0)
}