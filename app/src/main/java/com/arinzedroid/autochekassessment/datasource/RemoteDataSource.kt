package com.arinzedroid.autochekassessment.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.arinzedroid.autochekassessment.model.*
import com.arinzedroid.autochekassessment.service.ApiService
import com.arinzedroid.autochekassessment.service.HTTPErrorHandler
import com.arinzedroid.autochekassessment.utils.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val service: ApiService) {

    suspend fun getCarSearch(page: Int): GenericResponseStatus<List<SearchCarEntity?>?>{
         return withContext(Dispatchers.IO){
            try{
                val response = service.getCarSearchResponse(page)
                if(response.isSuccessful) {
                     val body = response.body()
                    GenericResponseStatus.OnSuccess(
                        body?.result?.map {
                            DataMapper.mapCarResponseToEntity(it, body.pagination?.currentPage)
                        })
                }
                else
                    GenericResponseStatus
                        .OnFailed(HTTPErrorHandler.handleErrorWithCode(response))
            }catch (ex: Exception){
                ex.printStackTrace()
                GenericResponseStatus
                    .OnFailed(HTTPErrorHandler.httpFailWithCode(ex))
            }
        }
    }

    suspend fun getPopularCars(page: Int): GenericResponseStatus<List<PopularCarEntity?>?> {
        return withContext(Dispatchers.IO){
            try{
                val response = service.getPopularCarResponse(page)
                if(response.isSuccessful) {
                    val body = response.body()
                    GenericResponseStatus.OnSuccess(
                        body?.result?.map {
                            DataMapper.mapPopularCarEntity(it, body.pagination?.currentPage)
                        }
                    )
                }
                else
                    GenericResponseStatus
                        .OnFailed(HTTPErrorHandler.handleErrorWithCode(response))
            }catch (ex: Exception){
                ex.printStackTrace()
                GenericResponseStatus
                    .OnFailed(HTTPErrorHandler.httpFailWithCode(ex))
            }
        }
    }

    suspend fun getCarDetails(carId: String): GenericResponseStatus<CarDetailResponse?> {
        return withContext(Dispatchers.IO){
            try{
                val response = service.getCarDetailsResponse(carId = carId)
                if(response.isSuccessful)
                    GenericResponseStatus.OnSuccess(response.body())
                else
                    GenericResponseStatus.OnFailed(HTTPErrorHandler.handleErrorWithCode(response))
            }catch (ex: Exception){
                ex.printStackTrace()
                GenericResponseStatus.OnFailed(HTTPErrorHandler.httpFailWithCode(ex))
            }
        }

    }

    suspend fun getAllCarMedia(carId: String,page: Int):GenericResponseStatus<List<MediaEntity?>?> {
        return withContext(Dispatchers.IO){
            try{
                val response = service.getAllCarMedia(carId,page)
                if(response.isSuccessful) {
                    val body = response.body()
                    GenericResponseStatus.OnSuccess(
                        body?.result?.map {
                            DataMapper.mapMediaToEntity(it, body.pagination?.currentPage,carId)
                        }
                    )
                }
                else
                    GenericResponseStatus
                        .OnFailed(HTTPErrorHandler.handleErrorWithCode(response))
            }catch (ex: Exception){
                ex.printStackTrace()
                GenericResponseStatus
                    .OnFailed(HTTPErrorHandler.httpFailWithCode(ex))
            }
        }
    }

}