package com.saradar.loader

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.custom_loader_dialog.view.*

class CustomLoader : DialogFragment() {

    private var loaderColor: Int = 0
    private var loaderMessage: String = ""
    private var blockScreen: Boolean = false

    companion object {

        private lateinit var context: Context
        private lateinit var customLoader: CustomLoader

        fun newInstance(context: Context): CustomLoader {
            customLoader = CustomLoader()
            this.context = context
            return customLoader
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.custom_loader_dialog, null, false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // region "ProgressBar Color"
        if (loaderColor != 0) {
            view.pb_loader.indeterminateTintList = ColorStateList.valueOf(loaderColor)
        }
        // endregion

        // region "Loader Message"
        if (loaderMessage.isNotEmpty()) {
            view.tv_loaderMessage.visibility = View.VISIBLE
            view.tv_loaderMessage.text = loaderMessage
        }
        // endregion

        // region "Disable Screen Touch"
        if (blockScreen) {
            disableScreenTouch()
        }
        // endregion

        return view
    }

    // region "Setters"
    fun setLoaderColor(loaderColor: Int) {
        this.loaderColor = loaderColor
    }

    fun setLoaderMessage(loaderMessage: String) {
        this.loaderMessage = loaderMessage
    }

    fun setBlockScreen(blockScreen: Boolean) {
        this.blockScreen = blockScreen
    }
    // endregion

    fun show(fragmentManager: FragmentManager) {
        customLoader.show(fragmentManager, "")
    }

    private fun disableScreenTouch() {
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
    }

    private fun enableScreenTouch() {
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
    }

    private fun resetFlagsToDefault() {
        enableScreenTouch()
        blockScreen = false
        loaderMessage = ""
        view?.tv_loaderMessage?.visibility = View.GONE
    }

    // region "Overridden Functions"
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return object : Dialog(context) {
            override fun onBackPressed() {
                if (!blockScreen) {
                    super.onBackPressed()
                }
            }
        }
    }

    override fun onDismiss(dialog: DialogInterface?) {
        super.onDismiss(dialog)
        resetFlagsToDefault()
    }
    // endregion

}