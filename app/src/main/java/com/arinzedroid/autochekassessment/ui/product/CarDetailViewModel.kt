package com.arinzedroid.autochekassessment.ui.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arinzedroid.autochekassessment.model.CarDetailResponse
import com.arinzedroid.autochekassessment.model.GenericResponseStatus
import com.arinzedroid.autochekassessment.repo.CarRepo
import com.arinzedroid.autochekassessment.ui.BaseViemodel
import javax.inject.Inject

class CarDetailViewModel @Inject constructor(
    private val carRepo: CarRepo
) : BaseViemodel<GenericResponseStatus<CarDetailResponse?>>() {

    fun getCarDetails(carID: String){
        loadData { carRepo.getCarDetail(carID) }
    }
}