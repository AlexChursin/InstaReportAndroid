package com.example.instareport.activities

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.arellomobile.mvp.MvpAppCompatActivity
import com.example.instareport.R
import com.example.instareport.utils.SessionManager

import com.shrikanthravi.library.NightModeButton

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_reg_main.*


class MainActivity : AppCompatActivity() {

    var startMode = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    setupPermissions()

        nightModeButton.setOnSwitchListener { isNight ->
            if (isNight) {
                //Function to change color

                Toast.makeText(applicationContext, "Night Mode On", Toast.LENGTH_SHORT).show()
            } else {
                //Function to change color

                Toast.makeText(applicationContext, "Night Mode Off", Toast.LENGTH_SHORT).show()
            }
        }
        reg_main_input.setOnClickListener {

            val intent = Intent(this, WebViewStart::class.java)
            startActivity(intent)

            // NavHostFragment.findNavController(this).navigate(R.id.action_regMainFragment_to_webViewStart)
        }
    }
    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(this,
            Manifest.permission.INTERNET)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            Log.i("permission!!!", "Permission to record denied")
        }
    }
    private fun animateStatusActionBar(color: Int, colorTo: Int) {

    }


}