package com.arinzedroid.autochekassessment.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.arinzedroid.autochekassessment.db.dao.CarDao
import com.arinzedroid.autochekassessment.db.dao.MediaDao
import com.arinzedroid.autochekassessment.db.dao.PopularCarDao
import com.arinzedroid.autochekassessment.db.dao.StatsDao
import com.arinzedroid.autochekassessment.model.MediaEntity
import com.arinzedroid.autochekassessment.model.PopularCarEntity
import com.arinzedroid.autochekassessment.model.SearchCarEntity
import com.arinzedroid.autochekassessment.model.StatsEntity

@Database(entities = [StatsEntity::class,
    SearchCarEntity::class, MediaEntity::class,
    PopularCarEntity::class],
    version = 1, exportSchema = true)
abstract class AppDatabase: RoomDatabase() {

    abstract fun statDao(): StatsDao
    abstract fun carDao(): CarDao
    abstract fun popularCarDao(): PopularCarDao
    abstract fun mediaDao(): MediaDao

    companion object{
        private var databaseInstance: AppDatabase? = null

        fun buildDatabase(context: Context): AppDatabase{
            if(databaseInstance == null){
                synchronized(this){
                    databaseInstance = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "Autochek_db")
                        .build()
                }
            }
            return databaseInstance!!
        }


    }
}