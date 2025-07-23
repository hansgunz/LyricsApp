package com.example.lyricsapp.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lyricsapp.presentation.screen.common.LyricsItem

@Composable
fun LyricsDatailScreen(
    modifier: Modifier = Modifier,
    artist: String,
    song: String,
    lyrics: String
){
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ){
        LyricsItem(
            modifier = Modifier,
            artist = artist,
            song = song,
            lyrics = lyrics
        )
    }
}