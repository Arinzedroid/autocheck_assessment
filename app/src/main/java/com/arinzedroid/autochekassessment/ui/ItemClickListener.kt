package com.arinzedroid.autochekassessment.ui

import com.arinzedroid.autochekassessment.model.CarDetailResponse

interface ItemClickListener<T> {
    fun onItem(item: T)
}

interface ProductDetailClickListener{
    fun onGoBack()
    fun viewMedia(item: CarDetailResponse)
    fun onInterested(item: CarDetailResponse)
    fun onFinancing(item: CarDetailResponse)
}