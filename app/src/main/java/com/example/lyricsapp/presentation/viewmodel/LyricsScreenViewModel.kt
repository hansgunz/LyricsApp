package com.example.lyricsapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lyricsapp.domain.usecase.GetLyricsUseCase
import com.example.lyricsapp.domain.usecase.InsertLyricsUseCase
import com.example.lyricsapp.domain.usecase.result.DataError
import com.example.lyricsapp.domain.usecase.result.Result
import com.example.lyricsapp.presentation.viewmodel.states.LyricsScreenState
import com.example.lyricsapp.presentation.viewmodel.utils.asUiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LyricsScreenViewModel @Inject constructor(
    private val getLyricsUseCase: GetLyricsUseCase,
    private val insertLyricsUseCase: InsertLyricsUseCase
): ViewModel(){

    private val _lyricsScreenState = MutableStateFlow(LyricsScreenState())
    val lyricsScreenState = _lyricsScreenState.asStateFlow()

    fun fetchLyricsByArtist(artist: String, song: String){
        viewModelScope.launch(Dispatchers.IO) {
            getLyricsUseCase(artist = artist, title = song).collect{ result ->
                when(result){
                    is Result.Success -> {
                        setLyrics(result.data.lyrics)
                    }
                    is Result.Error -> {
                        setError(result.error)
                    }
                    is Result.Loading -> {
                        setIsLoading()
                    }
                }
            }
        }
    }

    fun insertLyrics(artist: String, song: String, lyrics: String){
        viewModelScope.launch(Dispatchers.IO){
            insertLyricsUseCase(artist = artist, song = song, lyrics = lyrics)
        }
    }

    private fun setLyrics(lyrics: String){
        _lyricsScreenState.update {
            it.copy(
                lyrics = lyrics,
                isLoading = false
            )
        }
    }

    private fun setIsLoading(){
        _lyricsScreenState.update {
            it.copy(
                isLoading = true
            )
        }
    }

    private fun setError(error: DataError.Network){
        _lyricsScreenState.update{
            it.copy(
                isLoading = false,
                error = error.asUiText()
            )
        }
    }
}