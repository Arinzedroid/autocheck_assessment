package com.arinzedroid.autochekassessment.ui.home

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.arinzedroid.autochekassessment.model.PopularCarEntity
import com.arinzedroid.autochekassessment.repo.CarRepo
import com.arinzedroid.autochekassessment.ui.BaseViemodel
import javax.inject.Inject

class StatusViewModel @Inject constructor(
    repo: CarRepo
) :BaseViemodel<LiveData<PagedList<PopularCarEntity>>>() {

    init {
        loadData { repo.getPopularCars() }
    }
}