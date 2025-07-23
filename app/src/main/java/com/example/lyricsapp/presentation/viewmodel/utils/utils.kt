package com.example.lyricsapp.presentation.viewmodel.utils

import com.example.lyricsapp.R
import com.example.lyricsapp.domain.usecase.result.DataError

fun DataError.asUiText(): UiText{
    return when(this){
        DataError.Network.REQUEST_TIMEOUT -> {
            UiText.StringResource(R.string.request_timeout_error_text)
        }
        DataError.Network.NO_INTERNET -> {
            UiText.StringResource(R.string.no_internet_connection_text)
        }
        DataError.Network.SERVER_ERROR -> {
            UiText.StringResource(R.string.server_error_text)
        }
        DataError.Network.SERIALIZATION -> {
            UiText.StringResource(R.string.serialization_text)
        }
        DataError.Network.BAD_REQUEST -> {
            UiText.StringResource(R.string.bad_request_error_text)
        }
        DataError.Network.UNKNOWN -> {
            UiText.StringResource(R.string.unknown_error_text)
        }
    }
}