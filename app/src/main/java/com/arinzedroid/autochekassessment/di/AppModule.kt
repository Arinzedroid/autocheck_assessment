package com.arinzedroid.autochekassessment.di

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.arinzedroid.autochekassessment.datasource.IDataSource
import com.arinzedroid.autochekassessment.datasource.LocalDataSource
import com.arinzedroid.autochekassessment.datasource.RemoteDataSource
import com.arinzedroid.autochekassessment.db.AppDatabase
import com.arinzedroid.autochekassessment.db.dao.CarDao
import com.arinzedroid.autochekassessment.db.dao.MediaDao
import com.arinzedroid.autochekassessment.db.dao.PopularCarDao
import com.arinzedroid.autochekassessment.db.dao.StatsDao
import com.arinzedroid.autochekassessment.model.CarDetailResponse
import com.arinzedroid.autochekassessment.model.PopularCarEntity
import com.arinzedroid.autochekassessment.model.SearchCarEntity
import com.arinzedroid.autochekassessment.repo.CarRepo
import com.arinzedroid.autochekassessment.service.ApiService
import com.arinzedroid.autochekassessment.utils.Utils
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context{
        return application
    }

    @Provides
    @Singleton
    fun provideApiService(httpClient: OkHttpClient, gson: Gson): ApiService{
        return Retrofit.Builder()
            .baseUrl(Utils.getEnvironmentBaseURL())
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideGsonBuilder(): Gson{
        return GsonBuilder().setLenient().create()
    }

    @Provides
    @Singleton
    fun provideHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient{
        return OkHttpClient.Builder()
            .followRedirects(true)
            .retryOnConnectionFailure(true)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideClient(): HttpLoggingInterceptor{
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideStatDao(context: Context): StatsDao{
        return AppDatabase.buildDatabase(context).statDao()
    }

    @Provides
    @Singleton
    fun provideCarDao(context: Context): CarDao{
        return AppDatabase.buildDatabase(context).carDao()
    }

    @Provides
    @Singleton
    fun providePopularCarDao(context: Context): PopularCarDao{
        return AppDatabase.buildDatabase(context).popularCarDao()
    }

    @Provides
    @Singleton
    fun providesMediaDao(context: Context): MediaDao{
        return AppDatabase.buildDatabase(context).mediaDao()
    }

}