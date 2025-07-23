package com.example.lyricsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.lyricsapp.presentation.screen.FavoritesScreen
import com.example.lyricsapp.presentation.screen.HomeScreen
import com.example.lyricsapp.presentation.screen.LyricsDatailScreen
import com.example.lyricsapp.presentation.screen.LyricsScreen

@Composable
fun MainNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
){
    NavHost( navController = navController, startDestination = ScreenNavigationItem.HomeScreenNavigationItem){
        composable<ScreenNavigationItem.HomeScreenNavigationItem> {
            HomeScreen(modifier = modifier){ artist, song ->
                navController.navigate(ScreenNavigationItem.LyricsScreenNavigationItem(artist, song))
            }
        }
        composable<ScreenNavigationItem.LyricsScreenNavigationItem> {
            val artist = it.toRoute<ScreenNavigationItem.LyricsScreenNavigationItem>().artist
            val song = it.toRoute<ScreenNavigationItem.LyricsScreenNavigationItem>().song

            LyricsScreen(modifier = modifier, artist = artist, song = song)
        }
        composable<ScreenNavigationItem.FavoritesScreenNavigationItem> {
            FavoritesScreen(
                modifier = modifier
            ){
                navController.navigate(
                    ScreenNavigationItem
                        .LyricsScreenDetailsNavigationItem(
                            artist = it.artist,
                            song = it.song,
                            lyrics = it.lyrics
                        )
                )
            }
        }
        composable<ScreenNavigationItem.LyricsScreenDetailsNavigationItem> {
            val artist = it.toRoute<ScreenNavigationItem.LyricsScreenDetailsNavigationItem>().artist
            val song = it.toRoute<ScreenNavigationItem.LyricsScreenDetailsNavigationItem>().song
            val lyrics = it.toRoute<ScreenNavigationItem.LyricsScreenDetailsNavigationItem>().lyrics

            LyricsDatailScreen(
                modifier = modifier,
                artist = artist,
                song = song,
                lyrics = lyrics
            )
        }
    }
}