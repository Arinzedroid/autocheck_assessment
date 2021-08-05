package com.arinzedroid.autochekassessment.db.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import com.arinzedroid.autochekassessment.model.MediaEntity

@Dao
abstract class MediaDao: BaseDao<MediaEntity> {

    @Query("SELECT * FROM MediaEntity ORDER BY currentPage ASC")
    abstract fun getAllMedia(): DataSource.Factory<Int, MediaEntity>

    @Query("SELECT Count() FROM MediaEntity WHERE carId = :carId")
    abstract fun getMediaCount(carId: String):Int
}