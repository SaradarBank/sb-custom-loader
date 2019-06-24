package com.saradar.loader

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.*
import android.widget.TextView

class CustomLoader : DialogFragment() {

    private var message: String? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return object : Dialog(activity!!, theme) {
            override fun onBackPressed() {}
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        dialog.setCanceledOnTouchOutside(false)

        val contextThemeWrapper = ContextThemeWrapper(activity, R.style.NoDimDialogFragmentStyle)
        val localInflater = inflater.cloneInContext(contextThemeWrapper)
        val view = localInflater.inflate(R.layout.loading_dialog, null, false)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val txt_message = view.findViewById<TextView>(R.id.txt_message)
        txt_message.text = message

        return view
    }

    fun setLoaderMessage(message: String) {
        this.message = message
    }

    override fun onDestroyView() {
        val dialog = dialog
        if (dialog != null && retainInstance) {
            dialog.setDismissMessage(null)
        }
        super.onDestroyView()
    }
}