package com.arinzedroid.autochekassessment.repo

import com.arinzedroid.autochekassessment.datasource.LocalDataSource
import com.arinzedroid.autochekassessment.datasource.RemoteDataSource
import com.arinzedroid.autochekassessment.model.GenericResponseStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CarRepo @Inject constructor (
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
    ){

    suspend fun getAllCars() = withContext(IO){
        checkAndFetchCars()

        localDataSource.getCarSearch()
    }

    suspend fun getPopularCars() = withContext(IO){
        checkAndFetchPopularCars()

        localDataSource.getPopularCars()

    }

    private suspend fun checkAndFetchPopularCars(){
        if(localDataSource.getPopularCarCount() <= 1){
            val res = remoteDataSource.getPopularCars(1)
            if(res is GenericResponseStatus.OnSuccess){
                localDataSource.savePopularCarsList(res.data?.filterNotNull().orEmpty())
            }
        }
    }

    private suspend fun checkAndFetchCars(){
        if(localDataSource.getCarCount() <= 1){
            val res = remoteDataSource.getCarSearch(1)
            if(res is GenericResponseStatus.OnSuccess){
                localDataSource.saveCarList(res.data?.filterNotNull().orEmpty())
            }
        }
    }

    private suspend fun checkAndFetchMedia(carId: String){
        if(localDataSource.getMediaCount(carId) <=1){
            val res = remoteDataSource.getAllCarMedia(carId,1)
            if(res is GenericResponseStatus.OnSuccess){
                localDataSource.saveCarMedia(res.data?.filterNotNull().orEmpty())
            }
        }
    }

    suspend fun getCarDetail(carId: String) = withContext(IO){
        remoteDataSource.getCarDetails(carId)
    }

    suspend fun getCarAllMedia(carId: String) = withContext(IO){
        checkAndFetchMedia(carId)

        localDataSource.getCarMedia(carId)
    }

}