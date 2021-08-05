package com.arinzedroid.autochekassessment.service

import com.arinzedroid.autochekassessment.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("inventory/car/search")
    suspend fun getCarSearchResponse(@Query("pageNumber")page: Int):
            Response<PaginatedResponse<Result>>

    @GET("inventory/make?popular=true")
    suspend fun getPopularCarResponse(@Query("pageNumber")page: Int):
            Response<PaginatedResponse<Make>>

    @GET("inventory/car/{carId}")
    suspend fun getCarDetailsResponse(@Path("carId") carId: String):
            Response<CarDetailResponse>

    @GET("inventory/car_media")
    suspend fun getAllCarMedia(@Query("carId") carId: String,
                               @Query("pageNumber") pageNumber: Int):
            Response<PaginatedResponse<Make>>
}