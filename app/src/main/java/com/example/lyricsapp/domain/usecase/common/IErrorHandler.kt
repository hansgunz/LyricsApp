package com.example.lyricsapp.domain.usecase.common

import com.example.lyricsapp.domain.usecase.result.DataError
import retrofit2.HttpException

interface IErrorHandler {

    fun httpErrorHandler(error: HttpException, tag: String): DataError.Network

    fun ioErrorHandler(tag: String): DataError.Network
}