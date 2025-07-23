package com.example.lyricsapp.presentation.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lyricsapp.R
import com.example.lyricsapp.presentation.screen.common.ErrorText
import com.example.lyricsapp.presentation.screen.common.LyricsItem
import com.example.lyricsapp.presentation.viewmodel.LyricsScreenViewModel
import com.example.lyricsapp.presentation.viewmodel.utils.UiText

@Composable
fun LyricsScreen(
    modifier: Modifier = Modifier,
    artist: String,
    song: String,
    viewModel: LyricsScreenViewModel = hiltViewModel()
){
    LaunchedEffect(key1 = true){
        viewModel.fetchLyricsByArtist(artist = artist, song = song)
    }
    val state by viewModel.lyricsScreenState.collectAsState()
    val context = LocalContext.current

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ){
        when{
            state.lyrics.isNotEmpty() -> {
                LyricsItem(
                    modifier = Modifier,
                    artist = artist,
                    song = song,
                    lyrics = state.lyrics
                )
                FloatingActionButton(
                    onClick = {
                        viewModel.insertLyrics(artist = artist, song = song, lyrics = state.lyrics)
                        Toast.makeText(
                            context,
                            context.getString(R.string.added_to_favorites_text),
                            Toast.LENGTH_LONG
                        ).show()
                              },
                    modifier = Modifier
                        .size(64.dp)
                        .align(Alignment.BottomEnd),
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_lyrics_heart),
                        contentDescription = "",
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }
            state.error != UiText.DynamicString("") -> {
                ErrorText(
                    modifier = Modifier,
                    text = state.error
                )
            }
            state.isLoading -> {
                CircularProgressIndicator()
            }
        }
    }
}