package com.saradar.loader

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

class CustomLoader : DialogFragment() {

    private var blockScreen = false

    companion object {
        lateinit var customLoader: CustomLoader
        fun newInstance(): CustomLoader {
            customLoader = CustomLoader()
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

        // region "Enable/Disable Screen Touch"
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
    // endregion¬

    fun show(fragmentManager: FragmentManager) {
        val ft = fragmentManager.beginTransaction()
        val prev = fragmentManager.findFragmentByTag("dialog")
        if (prev != null) {
            ft.remove(prev)
        }
        ft.addToBackStack(null)
        if (isAdded.not() || isVisible.not()) {
            customLoader.show(ft, "dialog")
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

    // region "Override Functions"
    override fun onSaveInstanceState(outState: Bundle) {
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return object : Dialog(context!!) {
            override fun onBackPressed() {
                if (blockScreen.not()) {
                    super.onBackPressed()
                }
            }
        }
    }

    override fun dismiss() {
        super.dismiss()
        blockScreen = false
    }
    // endregion
}