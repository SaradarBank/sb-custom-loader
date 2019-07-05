package com.saradar.showloading

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.saradar.loader.CustomLoader
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_show.setOnClickListener {
            val customLoader = CustomLoader()
            customLoader.setLoaderMessage("Please Wait")
            customLoader.show(supportFragmentManager, "")

            val handler = Handler()
            handler.postDelayed({
                customLoader.dismiss()
            }, 5000)
        }
    }
}