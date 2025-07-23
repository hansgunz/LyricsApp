package com.example.lyricsapp.domain.usecase.result

sealed interface DataError: Error {

    enum class Network: DataError{
        REQUEST_TIMEOUT,
        NO_INTERNET,
        SERVER_ERROR,
        SERIALIZATION,
        BAD_REQUEST,
        UNKNOWN
    }

    /*enum class IO: DataError{
        IO_ERROR
    }*/
}