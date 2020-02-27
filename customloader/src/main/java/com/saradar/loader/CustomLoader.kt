package com.saradar.loader

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

class CustomLoader : DialogFragment() {

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.custom_loader_dialog, null, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // region "Disable Screen Touch"
        enableScreenTouch()
        if (blockScreen) {
            disableScreenTouch()
        }
        // endregion

        return view
    }

    // region "Setters"
    fun setBlockScreen(blockScreen: Boolean) {
        this.blockScreen = blockScreen
    }
    // endregion

    fun show(fragmentManager: FragmentManager) {
        if (isAdded.not()) {
            customLoader.show(fragmentManager, "")
        }
    }

    private fun disableScreenTouch() {
        dialog?.setCancelable(false)
        dialog?.setCanceledOnTouchOutside(false)
    }

    private fun enableScreenTouch() {
        dialog?.setCancelable(true)
        dialog?.setCanceledOnTouchOutside(true)
    }

    // region "Overridden Functions"
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return object : Dialog(context!!) {
            override fun onBackPressed() {
                if (!blockScreen) {
                    super.onBackPressed()
                }
            }
        }
    }

    override fun dismiss() {
        blockScreen = false
        super.dismiss()
    }
    // endregion

}