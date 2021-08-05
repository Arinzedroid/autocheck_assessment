package com.arinzedroid.autochekassessment.datasource

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.PagingData
import androidx.paging.toLiveData
import com.arinzedroid.autochekassessment.db.dao.CarDao
import com.arinzedroid.autochekassessment.db.dao.MediaDao
import com.arinzedroid.autochekassessment.db.dao.PopularCarDao
import com.arinzedroid.autochekassessment.model.*
import com.arinzedroid.autochekassessment.repo.GenericBoundaryCallback
import com.arinzedroid.autochekassessment.service.ApiService
import com.arinzedroid.autochekassessment.utils.DataMapper
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import javax.inject.Inject


class LocalDataSource @Inject constructor(
    private val carDao: CarDao,
    private val popularCarDao: PopularCarDao,
    private val mediaDao: MediaDao,
    private val apiService: ApiService
) :IDataSource {

    override suspend fun getCarSearch():
            LiveData<PagedList<SearchCarEntity>> {
        return withContext(IO){

            val result = carDao.getAllCars()
            val boundary = GenericBoundaryCallback<SearchCarEntity, Result, SearchCarEntity>(
                loadPage = {apiService.getCarSearchResponse(it)},
                mapper = {data, currentPage ->
                    DataMapper.mapCarResponseToEntity(data,currentPage) },
                save = { carDao.insertAllData(it ?: emptyList()) }

            )
            result.toLiveData(
                pageSize = 10,
                boundaryCallback = boundary
            )
        }
    }

    override suspend fun getPopularCars(): LiveData<PagedList<PopularCarEntity>> {
        return withContext(IO){

            val result = popularCarDao.getAllCars()
            val boundary = GenericBoundaryCallback<PopularCarEntity, Make, PopularCarEntity>(
                loadPage = {apiService.getPopularCarResponse(it)},
                mapper = {data, currentPage ->
                    DataMapper.mapPopularCarEntity(data,currentPage) },
                save = { popularCarDao.insertAllData(it ?: emptyList()) }

            )

            result.toLiveData(
                pageSize = 10,
                boundaryCallback = boundary
            )
        }
    }

    override suspend fun getPopularCarCount(): Int {
        return popularCarDao.getCount()
    }

    override fun saveCar(data: SearchCarEntity) {
        TODO("Not yet implemented")
    }

    override fun saveCarList(data: List<SearchCarEntity>) {
        carDao.insertAllData(data)
    }

    override fun saveStat(data: StatsEntity) {
        TODO("Not yet implemented")
    }

    override fun saveStatList(data: List<StatsEntity>) {
        TODO("Not yet implemented")
    }

    override suspend fun getCarMedia(carId: String): LiveData<PagedList<MediaEntity>> {
        return withContext(IO){

            val result = mediaDao.getAllMedia()
            val boundary = GenericBoundaryCallback<MediaEntity, Make, MediaEntity>(
                loadPage = {apiService.getAllCarMedia(carId,it)},
                mapper = {data, currentPage ->
                    DataMapper.mapMediaToEntity(data,currentPage,carId) },
                save = { mediaDao.insertAllData(it ?: emptyList()) }

            )

            result.toLiveData(
                pageSize = 10,
                boundaryCallback = boundary
            )
        }
    }

    override fun saveCarMedia(data: List<MediaEntity>) {
        mediaDao.insertAllData(data)
    }

    override suspend fun getCarCount(): Int {
        return carDao.getCarCount()
    }

    override fun savePopularCar(data: PopularCarEntity) {
        popularCarDao.insertData(data)
    }

    override fun savePopularCarsList(data: List<PopularCarEntity>) {
        popularCarDao.insertAllData(data)
    }

    override suspend fun getMediaCount(carId: String): Int {
        return mediaDao.getMediaCount(carId = carId)
    }


}