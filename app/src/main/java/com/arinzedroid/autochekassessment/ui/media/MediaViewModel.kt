package com.arinzedroid.autochekassessment.ui.media

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.arinzedroid.autochekassessment.model.MediaEntity
import com.arinzedroid.autochekassessment.repo.CarRepo
import com.arinzedroid.autochekassessment.ui.BaseViemodel
import javax.inject.Inject

class MediaViewModel @Inject constructor(
    private val carRepo: CarRepo
) : BaseViemodel<LiveData<PagedList<MediaEntity>>>() {


    fun fetchCarMedia(carId: String){
        loadData { carRepo.getCarAllMedia(carId) }
    }

}