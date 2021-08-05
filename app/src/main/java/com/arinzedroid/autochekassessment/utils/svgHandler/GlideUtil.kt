package com.arinzedroid.autochekassessment.utils.svgHandler

import android.content.Context
import android.graphics.drawable.PictureDrawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.arinzedroid.autochekassessment.R
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions


object GlideUtil {

    private fun getBuilder(context: Context): RequestBuilder<PictureDrawable>{
        return GlideApp.with(context)
            .`as`(PictureDrawable::class.java)
            .placeholder(R.drawable.ic_baseline_image)
            .error(R.drawable.ic_baseline_broken_image)
            .transition(withCrossFade())
            .listener(SvgSoftwareLayerSetter())
    }

    private fun getGlideRequestOptions(crop: Boolean = true): RequestOptions {
        return if(crop)
            RequestOptions()
                .placeholder(R.drawable.ic_baseline_image)
                .error(R.drawable.ic_baseline_broken_image)
                .circleCrop()
        else
            RequestOptions()
                .placeholder(R.drawable.ic_baseline_image)
                .error(R.drawable.ic_baseline_broken_image)
    }

    fun setImageIntoView(context: Context, url: String?, view: ImageView?, crop: Boolean){
        try {
            Glide.with(context)
                .load(url)
                .apply(getGlideRequestOptions(crop))
                .into(view ?: return)
        }catch (ex: Exception){
            getBuilder(context).load(url).into(view ?: return)
            ex.printStackTrace()
        }
    }

    fun setDrawableIntoView(context: Context, @DrawableRes url: Int?, view: ImageView?, crop: Boolean){
        Glide.with(context)
            .load(url)
            .apply(getGlideRequestOptions(crop))
            .into(view ?: return)
    }
}