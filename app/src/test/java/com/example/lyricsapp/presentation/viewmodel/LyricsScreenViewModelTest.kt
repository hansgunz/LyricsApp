package com.example.lyricsapp.presentation.viewmodel

import app.cash.turbine.test
import com.example.lyricsapp.data.local.dao.LyricsDao
import com.example.lyricsapp.data.remote.api.LyricApi
import com.example.lyricsapp.data.remote.model.DataDTO
import com.example.lyricsapp.domain.model.Data
import com.example.lyricsapp.domain.repository.LyricsRepository
import com.example.lyricsapp.domain.usecase.GetLyricsUseCase
import com.example.lyricsapp.domain.usecase.InsertLyricsUseCase
import com.example.lyricsapp.domain.usecase.common.IErrorHandler
import com.example.lyricsapp.domain.usecase.result.Result
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class LyricsScreenViewModelTest {

    private lateinit var getLyricsUseCase: GetLyricsUseCase
    private lateinit var insertLyricsUseCase: InsertLyricsUseCase
    private lateinit var lyricsScreenViewModel: LyricsScreenViewModel
    private lateinit var lyricsRepository: LyricsRepository
    private lateinit var errorHandler: IErrorHandler
    private lateinit var lyricsApi: LyricApi
    private lateinit var lyricsDao: LyricsDao

    @Before
    fun setUp() {
        getLyricsUseCase = Mockito.mock(GetLyricsUseCase::class.java)
        insertLyricsUseCase = Mockito.mock(InsertLyricsUseCase::class.java)
        lyricsRepository = Mockito.mock(LyricsRepository::class.java)
        errorHandler = Mockito.mock(IErrorHandler::class.java)
        lyricsApi = Mockito.mock(LyricApi::class.java)
        lyricsDao = Mockito.mock(LyricsDao::class.java)
        lyricsScreenViewModel = LyricsScreenViewModel(
            getLyricsUseCase = getLyricsUseCase,
            insertLyricsUseCase = insertLyricsUseCase
        )
    }

    @After
    fun tearDown() {
    }

    @Test
    fun verifyFetchLyricsByArtistIsSuccess() = runTest {
        val artist = "the strokes"
        val song = "juicebox"
        val lyricsString = "Everybody sees me\r\nBut its not that easy\r\nStanding in the lightfield\r\nStanding in the lightfield\r\nWaiting for some action\r\nWaiting for some action\n\nWhy wont you come over here\n\n\n\nWhy wont you come over here\n\nWe've got a city to love\n\nWhy wont you come over here\n\nWe've got a city to love\n\n\n\nOld time grudges\n\nWill die so slowly\n\nI know you miss the\n\nWay I saw you\n\nAnd cold\n\nYou're so cold\n\nYou're so cold\n\nYou're so cold\n\n\n\nNobody can see me\n\nEverythings too easy\n\nStanding in the lightfield\n\nStanding in the lightfield\n\nWaiting for some actress\n\nWaiting for some actress\n\nTo say\n\nWhy wont you come over here\n\n\n\nWhy wont you come over here\n\nWe've got a city to love\n\nWhy wont you come over here\n\nWe've got a city to love\n\n\n\nOld time love song\n\nWill die so swiftly\n\nYou never trust me\n\nFor a while\n\nIt was nice\n\nBut its time to say bye\n\n\n\nCold\n\nYou're so cold\n\nYou're so cold\n\nYou're so cold\n\n\n\nNO NO NO\n\nYou're so cold\n\nYou're so cold\n\nYou're so cold\n\n\n\nNO NO NO NO\n\nYou're so cold\n\nYou're so cold\n\nYou're so cold\n\n\n\nOh\n\nYou're so cold"
        val data = Data(
            lyricsString
        )
        val dataDTO =
            DataDTO(
                lyricsString
            )

        Mockito.`when`(getLyricsUseCase(artist, song)).thenReturn(flow {
            emit(Result.Loading())
            emit(Result.Success(data))
        })
        Mockito.`when`(lyricsRepository.getLyrics(artist, song)).thenReturn(
             dataDTO
        )
        Mockito.`when`(lyricsApi.getLyrics(artist, song)).thenReturn(
            dataDTO
        )
        lyricsScreenViewModel.fetchLyricsByArtist(artist, song)
        getLyricsUseCase(artist, song).test {
            val firstItem = awaitItem()
            val secondItem = awaitItem()
            Assert.assertTrue(firstItem is Result.Loading)
            Assert.assertTrue(secondItem is Result.Success)
        }
    }

}