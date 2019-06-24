package com.saradar.loader

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView

class CustomLoader : DialogFragment() {

    private var message: String = ""

    companion object {
        var progressBarColor: Int = 0

        fun newInstance(progressBarColor: Int): CustomLoader {
            val customLoader = CustomLoader()
            this.progressBarColor = progressBarColor
            return customLoader
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.custom_loader_dialog, null, false)

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // region "ProgressBar Color"
        val pb_customLoader = view.findViewById<ProgressBar>(R.id.pb_customLoader)
        if (progressBarColor != 0) {
            pb_customLoader.indeterminateTintList = ColorStateList.valueOf(progressBarColor)
        }
        // endregion

        // region "Loader Message"
        val tv_loaderMessage = view.findViewById<TextView>(R.id.tv_loaderMessage)
        if (message.isNotEmpty()) {
            tv_loaderMessage.visibility = View.VISIBLE
            tv_loaderMessage.text = message
            message = ""
        } else {
            tv_loaderMessage.visibility = View.GONE
        }
        // endregion

        return view
    }

    fun setLoaderMessage(message: String) {
        this.message = message
    }
}