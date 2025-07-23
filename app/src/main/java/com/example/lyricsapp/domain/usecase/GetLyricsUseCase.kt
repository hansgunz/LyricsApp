package com.example.lyricsapp.domain.usecase

import com.example.lyricsapp.domain.model.Data
import com.example.lyricsapp.domain.usecase.result.DataError
import com.example.lyricsapp.domain.usecase.result.Result
import kotlinx.coroutines.flow.Flow

interface GetLyricsUseCase {

    operator fun invoke(artist: String, title: String): Flow<Result<Data, DataError.Network>>

}