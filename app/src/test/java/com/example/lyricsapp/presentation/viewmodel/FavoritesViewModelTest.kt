package com.example.lyricsapp.presentation.viewmodel

import app.cash.turbine.test
import com.example.lyricsapp.domain.model.LyricsData
import com.example.lyricsapp.domain.usecase.FetchLyricsFromLocalUseCase
import com.example.lyricsapp.domain.usecase.result.Result
import com.example.lyricsapp.presentation.viewmodel.utils.CoroutineTestRule
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FavoritesViewModelTest {

    private lateinit var favoritesViewModel: FavoritesViewModel
    private lateinit var fetchLyricsFromLocalUseCase: FetchLyricsFromLocalUseCase

    @get:Rule
    val coroutineTestRule: CoroutineTestRule = CoroutineTestRule()

    @Before
    fun setUp() {
        fetchLyricsFromLocalUseCase = mockk()
        favoritesViewModel = FavoritesViewModel(
            fetchLyricsFromLocalUseCase,
            coroutineTestRule.testDispatcherProvider
        )
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun fetchLyricsFromLocalIsSuccess() = runTest {
        val lyricsList = listOf(
            LyricsData(id = 1, artist = "The Strokes", song = "Reptilia", lyrics = "urfbnhjrnfhbrghfjberjfghbdrfg"),
            LyricsData(id = 2, artist = "Mordecai and the Rigbys", song = "Party Tonight", lyrics = "nfjhbreufaijrfbjbkljnarfljnjrb")
        )

        coEvery {
            fetchLyricsFromLocalUseCase()
        } returns flow {
            emit(Result.Loading())
            emit(Result.Success(lyricsList))
        }

        favoritesViewModel.favoritesState.test {
            awaitItem()
            favoritesViewModel.fetchLyricsFromLocal()
            advanceUntilIdle()
            awaitItem()
            assertEquals(lyricsList, awaitItem().lyricsList)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun fetchLyricsFromLocalIsLoading() = runTest {
        val lyricsList = listOf(
            LyricsData(id = 1, artist = "The Strokes", song = "Reptilia", lyrics = "urfbnhjrnfhbrghfjberjfghbdrfg"),
            LyricsData(id = 2, artist = "Mordecai and the Rigbys", song = "Party Tonight", lyrics = "nfjhbreufaijrfbjbkljnarfljnjrb")
        )

        coEvery {
            fetchLyricsFromLocalUseCase()
        } returns flow {
            emit(Result.Loading())
            emit(Result.Success(lyricsList))
        }

        favoritesViewModel.favoritesState.test {
            awaitItem()
            favoritesViewModel.fetchLyricsFromLocal()
            advanceUntilIdle()
            assertTrue(awaitItem().isLoading)
            cancelAndIgnoreRemainingEvents()
        }
    }
}