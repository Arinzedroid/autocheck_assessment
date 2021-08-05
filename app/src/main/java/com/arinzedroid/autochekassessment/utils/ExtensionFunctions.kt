package com.arinzedroid.autochekassessment.utils

import android.app.Activity
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.arinzedroid.autochekassessment.di.AppComponents
import com.arinzedroid.autochekassessment.di.DaggerAppComponents
import java.util.*


fun Activity.getAppComponent(): AppComponents {
    val appComponent: AppComponents by lazy {
        // Creates an instance of AppComponent using its Factory constructor
        // We pass the applicationContext that will be used as Context in the graph
        DaggerAppComponents.factory().create(application)
    }
    return appComponent
}

fun Fragment.getAppComponent(): AppComponents {
    val appComponent: AppComponents by lazy {
        // Creates an instance of AppComponent using its Factory constructor
        // We pass the applicationContext that will be used as Context in the graph
        DaggerAppComponents.factory().create(requireActivity().application)
    }
    return appComponent
}

fun Fragment.toast(msg: String){
    Toast.makeText(requireContext(),msg, Toast.LENGTH_LONG).show()
}

fun String.capitalizeWords(): String = split(" ").joinToString(" ") { t ->
    t.replaceFirstChar {
    if (it.isLowerCase()) it.titlecase(
        Locale.getDefault()
    ) else it.toString()
} }
