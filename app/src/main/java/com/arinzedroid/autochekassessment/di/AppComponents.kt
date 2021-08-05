package com.arinzedroid.autochekassessment.di

import android.app.Application
import android.content.Context
import com.arinzedroid.autochekassessment.MainActivity
import com.arinzedroid.autochekassessment.repo.CarRepo
import com.arinzedroid.autochekassessment.ui.home.HomeFragment
import com.arinzedroid.autochekassessment.ui.media.MediaFragment
import com.arinzedroid.autochekassessment.ui.product.CarDetailsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Component(modules = [AppModule::class])
@Singleton
interface AppComponents {

    //Factory to create instances of AppComponents
    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Application): AppComponents
    }

    fun inject(activity: MainActivity)
    fun inject(homeFragment: HomeFragment)
    fun inject(carDetailsFragment: CarDetailsFragment)
    fun inject(mediaFragment: MediaFragment)
}