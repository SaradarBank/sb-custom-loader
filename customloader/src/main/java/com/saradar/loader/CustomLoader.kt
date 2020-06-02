package com.saradar.loader

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class CustomLoader : DialogFragment() {

    private var blockScreen = false
    private var isShown = false

    companion object {
        lateinit var customLoader: CustomLoader
        fun newInstance(): CustomLoader {
            customLoader = CustomLoader()
            return customLoader
        }
    }

    @SuppressLint("InflateParams")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.custom_loader_dialog, null, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return view
    }

    // region "Setters"
    fun setBlockScreen(blockScreen: Boolean) {
        this.blockScreen = blockScreen
    }
    // endregion

    fun show(fragmentManager: FragmentManager) {
        if (isShown.not()) {
            isShown = true
            if (blockScreen) {
                disableScreenTouch()
            } else {
                enableScreenTouch()
            }
            dialog?.setCanceledOnTouchOutside(false) // always cancel touches
            customLoader.show(fragmentManager, "loader")
        }

    }

    private fun disableScreenTouch() {
        this.isCancelable = false
        dialog?.setCancelable(false)
    }

    private fun enableScreenTouch() {
        this.isCancelable = true
        dialog?.setCancelable(true)
    }

    // region "Override Functions"
    override fun show(manager: FragmentManager, tag: String?) {
        val fm: FragmentManager = manager
        val oldFragment: Fragment? = fm.findFragmentByTag("loader")
        if (oldFragment != null) {
            fm.beginTransaction().remove(oldFragment).commit()
        }
        val ft = manager.beginTransaction()
        ft.add(this, "loader")
        ft.commitAllowingStateLoss()
    }

    override fun dismiss() {
        super.dismiss()
        blockScreen = false
        isShown = false
    }

    override fun onSaveInstanceState(outState: Bundle) {
    }
    // endregion
}