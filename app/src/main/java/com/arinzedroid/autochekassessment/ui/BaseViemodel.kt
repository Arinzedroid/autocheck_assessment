package com.arinzedroid.autochekassessment.ui

import androidx.lifecycle.*
import com.arinzedroid.autochekassessment.model.ErrorModel
import com.arinzedroid.autochekassessment.service.HTTPErrorHandler
import kotlinx.coroutines.launch

abstract class BaseViemodel <T>: ViewModel() {
    protected val _response = MutableLiveData<T>()
    val response = _response

    protected val _error = MutableLiveData<ErrorModel>()
    val error = _error

    protected val _loader = MutableLiveData<Boolean>()
    val loader = _loader

    protected fun loadData(action: suspend () -> T?){
        viewModelScope.launch {
            try{
                _loader.value = true
                _response.value = action()
            }catch (ex: Exception){
                _error.value = HTTPErrorHandler.httpFailWithCode(ex)
            }
            finally {
                _loader.value = false
            }
        }

    }
}