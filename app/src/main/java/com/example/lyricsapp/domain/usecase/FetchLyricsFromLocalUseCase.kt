package com.example.lyricsapp.domain.usecase

import com.example.lyricsapp.domain.model.LyricsData
import com.example.lyricsapp.domain.usecase.result.DataError
import com.example.lyricsapp.domain.usecase.result.Result
import kotlinx.coroutines.flow.Flow

interface FetchLyricsFromLocalUseCase {

    suspend operator fun invoke(): Flow<Result<List<LyricsData>, DataError>>
}