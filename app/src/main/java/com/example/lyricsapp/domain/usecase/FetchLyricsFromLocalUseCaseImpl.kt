package com.example.lyricsapp.domain.usecase

import com.example.lyricsapp.domain.model.LyricsData
import com.example.lyricsapp.domain.repository.LyricsRepository
import com.example.lyricsapp.domain.usecase.common.ErrorHandler
import com.example.lyricsapp.domain.usecase.common.IErrorHandler
import com.example.lyricsapp.domain.usecase.result.DataError
import com.example.lyricsapp.domain.usecase.result.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class FetchLyricsFromLocalUseCaseImpl @Inject constructor(
    private val repository: LyricsRepository,
    private val errorHandler: IErrorHandler
) : FetchLyricsFromLocalUseCase {
    private val TAG = this::class.simpleName

    override suspend fun invoke(): Flow<Result<List<LyricsData>, DataError>> = flow{
        try {
            emit(Result.Loading())
            val lyricsList = repository.fetchLyricsFromLocal().map {
                it.toLyrics()
            }
            emit(Result.Success(lyricsList))
        } catch (e: IOException){
            //research about how to handle errors with room
        }
    }
}