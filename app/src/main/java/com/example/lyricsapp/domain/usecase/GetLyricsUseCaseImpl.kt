package com.example.lyricsapp.domain.usecase

import com.example.lyricsapp.domain.model.Data
import com.example.lyricsapp.domain.repository.LyricsRepository
import com.example.lyricsapp.domain.usecase.common.ErrorHandler
import com.example.lyricsapp.domain.usecase.common.IErrorHandler
import com.example.lyricsapp.domain.usecase.result.DataError
import com.example.lyricsapp.domain.usecase.result.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetLyricsUseCaseImpl @Inject constructor(
    private val lyricsRepository: LyricsRepository,
    private val errorHandler: IErrorHandler
): GetLyricsUseCase {
    private val TAG = this::class.simpleName ?: "GetLyricsUseCaseImpl"

    override fun invoke(artist: String, title: String): Flow<Result<Data, DataError.Network>> = flow {
        try {
            emit(Result.Loading())
            val lyrics = lyricsRepository.getLyrics(artist, title)
            emit(Result.Success(lyrics))
        } catch (e: HttpException){
            emit(Result.Error(errorHandler.httpErrorHandler(e, TAG)))
        } catch(e: IOException){
            emit(Result.Error(errorHandler.ioErrorHandler(TAG)))
        }
    }
}