package com.arinzedroid.autochekassessment.model

sealed class GenericResponseStatus<out T> {
    data class OnSuccess<T>(val data: T?) : GenericResponseStatus<T>()
    data class OnFailed(val error: ErrorModel): GenericResponseStatus<Nothing>()
}
