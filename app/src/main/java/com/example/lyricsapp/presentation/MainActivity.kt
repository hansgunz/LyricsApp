package com.example.lyricsapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.lyricsapp.presentation.navigation.MainNavGraph
import com.example.lyricsapp.presentation.navigation.ScreenNavigationItem
import com.example.lyricsapp.presentation.screen.BottomBar
import com.example.lyricsapp.presentation.screen.TopBar
import com.example.lyricsapp.presentation.theme.LyricsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val screenItems by lazy {
        listOf(ScreenNavigationItem.HomeScreenNavigationItem, ScreenNavigationItem.FavoritesScreenNavigationItem)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LyricsAppTheme {
                val navController = rememberNavController()
                Scaffold(
                    topBar = { TopBar() },
                    bottomBar = {
                        BottomBar(
                            bottomItems = screenItems
                        ){
                            navController.navigate(it)
                        }
                    }
                ) { innerPadding ->
                    MainNavGraph(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        navController = navController)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LyricsAppTheme {

    }
}