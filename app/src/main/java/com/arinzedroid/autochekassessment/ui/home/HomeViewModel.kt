package com.arinzedroid.autochekassessment.ui.home

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.PagingData
import com.arinzedroid.autochekassessment.model.PopularCarEntity
import com.arinzedroid.autochekassessment.model.SearchCarEntity
import com.arinzedroid.autochekassessment.repo.CarRepo
import com.arinzedroid.autochekassessment.ui.BaseViemodel
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repo: CarRepo
): BaseViemodel<LiveData<PagedList<SearchCarEntity>>>() {

    init {
        loadData { repo.getAllCars() }
    }
}