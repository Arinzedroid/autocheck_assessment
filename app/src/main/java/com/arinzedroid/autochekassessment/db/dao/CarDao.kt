package com.arinzedroid.autochekassessment.db.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import com.arinzedroid.autochekassessment.model.SearchCarEntity

@Dao
abstract class CarDao : BaseDao<SearchCarEntity> {

    @Query("SELECT * FROM SearchCarEntity ORDER BY currentPage ASC")
    abstract fun getAllCars(): DataSource.Factory<Int,SearchCarEntity>

    @Query("SELECT COUNT() from SearchCarEntity")
    abstract fun getCarCount(): Int
}