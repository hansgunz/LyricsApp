package com.example.lyricsapp.domain.usecase.common

import android.util.Log
import com.example.lyricsapp.domain.usecase.result.DataError
import retrofit2.HttpException

object ErrorHandler: IErrorHandler {

    override fun httpErrorHandler(error: HttpException, tag: String): DataError.Network{
        return when(error.code()){
            400 -> {
                Log.e(tag, "Error code: ${error.code()}")
                DataError.Network.BAD_REQUEST
            }
            408 -> {
                Log.e(tag, "Error code: ${error.code()}")
                DataError.Network.REQUEST_TIMEOUT
            }
            500 -> {
                Log.e(tag, "Error code: ${error.code()}")
                DataError.Network.SERVER_ERROR
            }
            else -> {
                Log.e(tag, "Error code: ${error.code()}")
                DataError.Network.UNKNOWN
            }
        }
    }

    override fun ioErrorHandler(tag: String): DataError.Network{
        Log.e(tag, "No internet connection")
        return DataError.Network.NO_INTERNET
    }
}