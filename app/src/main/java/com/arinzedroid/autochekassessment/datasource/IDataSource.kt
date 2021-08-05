package com.arinzedroid.autochekassessment.datasource

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.arinzedroid.autochekassessment.model.*

interface IDataSource {
    suspend fun getCarSearch(): LiveData<PagedList<SearchCarEntity>>
    suspend fun getPopularCars(): LiveData<PagedList<PopularCarEntity>>
    suspend fun getCarCount(): Int
    suspend fun getPopularCarCount(): Int
    suspend fun getMediaCount(carId: String):Int
    fun savePopularCarsList(data: List<PopularCarEntity>)
    fun savePopularCar(data: PopularCarEntity)
    fun saveCar(data: SearchCarEntity)
    fun saveCarList(data: List<SearchCarEntity>)
    fun saveStat(data: StatsEntity)
    fun saveStatList(data: List<StatsEntity>)
    suspend fun getCarMedia(carId: String):LiveData<PagedList<MediaEntity>>
    fun saveCarMedia(data: List<MediaEntity>)

}