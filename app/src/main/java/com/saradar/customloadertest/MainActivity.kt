package com.saradar.customloadertest

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.saradar.loader.CustomLoader
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_show.setOnClickListener {
            val customLoader = CustomLoader.newInstance(this)
            customLoader.setLoaderMessage("Please Wait")
            customLoader.setBlockScreen(true)
            customLoader.show(supportFragmentManager, "")

            val handler = Handler()
            handler.postDelayed({
                customLoader.dismiss()
            }, 5000)
        }

        btn_showFragment.setOnClickListener {
            val fragment = MainFragment()
            val manager = supportFragmentManager
            val transaction = manager.beginTransaction()
            transaction.add(R.id.container, fragment)
            transaction.commit()
        }
    }
}