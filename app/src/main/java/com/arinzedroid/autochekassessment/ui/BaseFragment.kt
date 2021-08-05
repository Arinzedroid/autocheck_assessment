package com.arinzedroid.autochekassessment.ui

import android.app.Dialog
import android.view.View
import androidx.fragment.app.Fragment
import com.arinzedroid.autochekassessment.R
import com.arinzedroid.autochekassessment.utils.Utils

abstract class BaseFragment : Fragment() {

    private var loader: Dialog? = null

    protected fun toggleBusyDialog(show: Boolean) {
        if (show) {
            if (loader == null) {
                val view = View.inflate(requireContext(), R.layout.view_busy_layout, null)
                loader = Utils.showPopUp(requireContext(), view)
            }
            loader?.show()
        } else {
            loader?.dismiss()
        }
    }
}