package com.saradar.customloadertest

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.saradar.loader.CustomLoader
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_show.setOnClickListener {
            val customLoader = CustomLoader.newInstance()
            customLoader.setBlockScreen(true)
            customLoader.show(fragmentManager!!)

            val handler = Handler()
            handler.postDelayed({
                customLoader.dismiss()
            }, 5000)
        }
    }
}