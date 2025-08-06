package com.example.lyricsapp.presentation.viewmodel.utils

import kotlinx.coroutines.CoroutineDispatcher

interface IDispatcherProvider {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}