package com.arinzedroid.autochekassessment.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.databinding.BindingAdapter
import com.arinzedroid.autochekassessment.R
import com.arinzedroid.autochekassessment.utils.svgHandler.GlideUtil
import com.bumptech.glide.Glide
import java.text.NumberFormat
import java.util.*

object Utils {

    fun getEnvironmentBaseURL():String{
        return "https://api.staging.myautochek.com/v1/"
    }

    fun formatCurrency(amt: Double?): String{
        val ft = NumberFormat.getCurrencyInstance()

        return when{
            (amt == null) ->
                "₦ --"
            (amt < 0) ->
                "₦-${ft.format(amt).substring(2)}"
            else ->
                "₦${ft.format(amt).substring(1)}"
        }
    }

    fun showPopUp(context: Context, view: View): Dialog {
        val builder = AlertDialog.Builder(context)
        builder.setView(view)
        val dialog = builder.create()
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

}

@BindingAdapter("loadStatusImage")
fun setStatusImage(view: ImageView, url: String?){
    GlideUtil.setImageIntoView(view.context, url = url, view = view, true)
}

@BindingAdapter("loadImage")
fun setImage(view: ImageView, url: String?){
    if(url != null){
        if(url.endsWith(".mp4")){
            view.setImageResource(R.drawable.ic_round_play)
        }else
            GlideUtil.setImageIntoView(view.context, url = url, view = view, false)
    }
}

@BindingAdapter("text")
fun setTextOnView(view: TextView, data: Double?){
    val _data = data ?: 0.0
    view.text = _data.toString().take(3)
}

@BindingAdapter("text")
fun setTextOnView(view: TextView, data: Int?){
    val _data = data ?: 0
    view.text = _data.toString().take(3)
}

@BindingAdapter("setPrice")
fun setPrice(view: TextView, price: Double?){
    view.text = Utils.formatCurrency(price)
}

@BindingAdapter("disableView")
fun disableView(view: View, disable: Boolean?){
    if(disable == true){
        view.isEnabled = false
        view.alpha = .5f
    }else{
        view.isEnabled = true
        view.alpha = 1f
    }
}

@BindingAdapter("hideView")
fun hideView(view: View, text: String?){
    val _text = text ?: ""
    if(_text.lowercase().endsWith(".mp4"))
        view.visibility = View.VISIBLE
    else
        view.visibility = View.GONE
}

@BindingAdapter("setCondition")
fun setCondition(view: TextView, condition: String?){
    val _condition = condition ?: ""
    view.apply {
        text = when{
            _condition.lowercase() == "new" -> "New"
            else -> "$_condition used".capitalizeWords()
        }
    }
}

@BindingAdapter(value = ["app:millage", "app:millageUnit"], requireAll = false)
fun setMillage(view: TextView, millage: Double?, millageUnit: String?){
    val _millage = millage ?: 0.0; val _millageUnit = millageUnit ?: ""
    val string = if(_millage.toString().length > 7)
        "$_millage \n $_millageUnit"
    else
        "$_millage $_millageUnit"
    view.text = string
}