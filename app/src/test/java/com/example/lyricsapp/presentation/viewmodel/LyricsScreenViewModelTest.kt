package com.example.lyricsapp.presentation.viewmodel

import androidx.compose.runtime.Composable
import app.cash.turbine.test
import com.example.lyricsapp.R
import com.example.lyricsapp.data.local.dao.LyricsDao
import com.example.lyricsapp.data.remote.api.LyricApi
import com.example.lyricsapp.data.remote.model.DataDTO
import com.example.lyricsapp.domain.model.Data
import com.example.lyricsapp.domain.repository.LyricsRepository
import com.example.lyricsapp.domain.usecase.GetLyricsUseCase
import com.example.lyricsapp.domain.usecase.InsertLyricsUseCase
import com.example.lyricsapp.domain.usecase.common.IErrorHandler
import com.example.lyricsapp.domain.usecase.result.DataError
import com.example.lyricsapp.domain.usecase.result.Result
import com.example.lyricsapp.presentation.viewmodel.states.LyricsScreenState
import com.example.lyricsapp.presentation.viewmodel.utils.CoroutineTestRule
import com.example.lyricsapp.presentation.viewmodel.utils.UiText
import com.example.lyricsapp.presentation.viewmodel.utils.asUiText
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class LyricsScreenViewModelTest {

    private lateinit var getLyricsUseCase: GetLyricsUseCase
    private lateinit var insertLyricsUseCase: InsertLyricsUseCase
    private lateinit var lyricsScreenViewModel: LyricsScreenViewModel
    private lateinit var errorHandler: IErrorHandler
    private val TAG = this::class.simpleName

    @get:Rule
    val coroutineTestRule: CoroutineTestRule = CoroutineTestRule()

    @Before
    fun setUp() {
        getLyricsUseCase = mockk()
        insertLyricsUseCase = mockk()
        errorHandler = mockk()
        lyricsScreenViewModel = LyricsScreenViewModel(
            getLyricsUseCase = getLyricsUseCase,
            insertLyricsUseCase = insertLyricsUseCase,
            dispatcherProvider = coroutineTestRule.testDispatcherProvider
        )
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun verifyFetchLyricsByArtistIsSuccess() = runTest {
        val artist = "the strokes"
        val song = "juicebox"
        val lyricsString = "Everybody sees me\r\nBut its not that easy\r\nStanding in the lightfield\r\nStanding in the lightfield\r\nWaiting for some action\r\nWaiting for some action\n\nWhy wont you come over here\n\n\n\nWhy wont you come over here\n\nWe've got a city to love\n\nWhy wont you come over here\n\nWe've got a city to love\n\n\n\nOld time grudges\n\nWill die so slowly\n\nI know you miss the\n\nWay I saw you\n\nAnd cold\n\nYou're so cold\n\nYou're so cold\n\nYou're so cold\n\n\n\nNobody can see me\n\nEverythings too easy\n\nStanding in the lightfield\n\nStanding in the lightfield\n\nWaiting for some actress\n\nWaiting for some actress\n\nTo say\n\nWhy wont you come over here\n\n\n\nWhy wont you come over here\n\nWe've got a city to love\n\nWhy wont you come over here\n\nWe've got a city to love\n\n\n\nOld time love song\n\nWill die so swiftly\n\nYou never trust me\n\nFor a while\n\nIt was nice\n\nBut its time to say bye\n\n\n\nCold\n\nYou're so cold\n\nYou're so cold\n\nYou're so cold\n\n\n\nNO NO NO\n\nYou're so cold\n\nYou're so cold\n\nYou're so cold\n\n\n\nNO NO NO NO\n\nYou're so cold\n\nYou're so cold\n\nYou're so cold\n\n\n\nOh\n\nYou're so cold"
        val data = Data(
            lyricsString
        )

        coEvery { getLyricsUseCase(artist, song) } returns flow {
            emit(Result.Loading())
            emit(Result.Success(data))
        }

        lyricsScreenViewModel.lyricsScreenState.test {
            awaitItem()
            lyricsScreenViewModel.fetchLyricsByArtist(artist, song)
            advanceUntilIdle()
            awaitItem()
            Assert.assertEquals(lyricsString, awaitItem().lyrics)
            cancelAndIgnoreRemainingEvents()
        }

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun verifyFetchLyricsByArtistIsLoading() = runTest {
        val artist = "the strokes"
        val song = "juicebox"

        coEvery { getLyricsUseCase(artist, song)} returns flow {
            emit(Result.Loading())
        }

        lyricsScreenViewModel.lyricsScreenState.test {
            awaitItem()
            lyricsScreenViewModel.fetchLyricsByArtist(artist, song)
            advanceUntilIdle()
            Assert.assertTrue(awaitItem().isLoading)
            cancelAndIgnoreRemainingEvents()
        }
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun verifyFetchLyricsByArtistError() = runTest {
        val artist = "the strokes"
        val song = "juicebox"

        every { errorHandler.ioErrorHandler(TAG ?: "") } returns DataError.Network.NO_INTERNET
        coEvery { getLyricsUseCase(artist, song) } returns flow {
            emit(Result.Loading())
            emit(Result.Error(errorHandler.ioErrorHandler(TAG ?: "")))
        }

        lyricsScreenViewModel.lyricsScreenState.test {
            awaitItem()
            lyricsScreenViewModel.fetchLyricsByArtist(artist, song)
            advanceUntilIdle()
            awaitItem()
            Assert.assertTrue(awaitItem().error != UiText.DynamicString(""))
            cancelAndIgnoreRemainingEvents()
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun verifyInsertLyricsIsCalled() = runTest {
        val artist = "Muse"
        val song = "Knights of Cydonia"
        val lyrics = "Everybody sees me\n" +
                "But its not that easy\n" +
                "Standing in the lightfield\n" +
                "Standing in the lightfield\n"

        coEvery { insertLyricsUseCase(artist = artist, song = song, lyrics = lyrics) } returns Unit

        lyricsScreenViewModel.insertLyrics(
            artist = artist,
            song = song,
            lyrics = lyrics
        )
        advanceUntilIdle()

        coVerify { insertLyricsUseCase(artist = artist, song = song, lyrics = lyrics) }
    }
}