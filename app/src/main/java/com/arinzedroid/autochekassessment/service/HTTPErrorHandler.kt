package com.arinzedroid.autochekassessment.service

import com.arinzedroid.autochekassessment.model.ErrorModel
import com.google.gson.Gson
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object HTTPErrorHandler {
    private const val unknownError = "An unknown error occurred trying to process this request. Please try again later"
    private const val connectError = "It appears your internet connection is slow or not connected. " +
            "Please connect to a stable internet and try again"
    private const val unknownHost = "Unknown host, could not connect to specified server"
    private const val timeOut = "Connection timed out: Please make sure internet is stable and try again"
    private const val successRequest = "Request successful"

    fun <T: Any?> handleErrorWithCode(response: Response<T>): ErrorModel{
        return try{
            if(!response.isSuccessful){
                val error: ErrorModel = Gson().fromJson(response.errorBody()?.string(), ErrorModel::class.java)
                error
            }else
                ErrorModel(
                    successRequest,"true")
        }catch (ex: Exception){
            ex.printStackTrace()
            ErrorModel(
                unknownError,"false")
        }
    }

    fun httpFailWithCode(t: Exception): ErrorModel{
        return when(t){
            is SocketTimeoutException -> {
                ErrorModel(message = timeOut,"false")
            }
            is UnknownHostException -> {
                ErrorModel(message = unknownHost,"false")
            }
            is ConnectException -> {
                ErrorModel(message = connectError,"false")
            }
            else ->  {
                ErrorModel(message = unknownError,"false")
            }
        }
    }
}