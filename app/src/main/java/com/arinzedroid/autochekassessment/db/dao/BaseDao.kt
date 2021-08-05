package com.arinzedroid.autochekassessment.db.dao

import androidx.room.*

@Dao
interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(data: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertAllData(data: List<T>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertDataAbortOnError(data: T)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    @JvmSuppressWildcards
    fun insertAllDataAbortOnError(data: List<T>)

    @Delete
    fun deleteData(data: T): Int?

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateDataIgnoreOnError(data: T): Int?

    @Update(onConflict = OnConflictStrategy.ABORT)
    fun updateDataAbortOnError(data: T): Int?

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateDataReplaceOnError(data: T): Int?

    @Update(onConflict = OnConflictStrategy.IGNORE)
    @JvmSuppressWildcards
    fun updateDataIgnoreOnError(data: List<T>): Int?

    @Update(onConflict = OnConflictStrategy.ABORT)
    @JvmSuppressWildcards
    fun updateDataAbortOnError(data: List<T>): Int?

    @Update(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun updateDataReplaceOnError(data: List<T>): Int?
}