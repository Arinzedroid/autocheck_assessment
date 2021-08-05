package com.arinzedroid.autochekassessment.db.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import com.arinzedroid.autochekassessment.model.PopularCarEntity

@Dao
abstract class PopularCarDao: BaseDao<PopularCarEntity> {

    @Query("SELECT * FROM PopularCarEntity ORDER BY currentPage ASC")
    abstract fun getAllCars(): DataSource.Factory<Int, PopularCarEntity>

    @Query("SELECT Count() FROM PopularCarEntity")
    abstract fun getCount(): Int

}