package com.example.lyricsapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lyricsapp.domain.model.LyricsData
import com.example.lyricsapp.domain.usecase.FetchLyricsFromLocalUseCase
import com.example.lyricsapp.domain.usecase.result.Result
import com.example.lyricsapp.presentation.viewmodel.states.FavoritesScreenState
import com.example.lyricsapp.presentation.viewmodel.utils.IDispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val fetchLyricsFromLocalUseCase: FetchLyricsFromLocalUseCase,
    private val dispatcherProvider: IDispatcherProvider
)
    : ViewModel() {

    private val _favoritesState = MutableStateFlow<FavoritesScreenState>(FavoritesScreenState())
    val favoritesState = _favoritesState.asStateFlow()

    fun fetchLyricsFromLocal(){
        viewModelScope.launch(dispatcherProvider.io){
            fetchLyricsFromLocalUseCase().collect{
                when(it){
                    is Result.Error -> {
                        TODO()
                    }
                    is Result.Loading -> {
                        setIsLoadingState()
                    }
                    is Result.Success -> {
                        setSuccessState(it.data)
                    }
                }
            }
        }
    }

    private fun setSuccessState(lyrics: List<LyricsData>){
        _favoritesState.update {
            it.copy(
                lyricsList = lyrics,
                isLoading = false
            )
        }
    }

    private fun setIsLoadingState(){
        _favoritesState.update {
            it.copy(
                isLoading = true
            )
        }
    }
}