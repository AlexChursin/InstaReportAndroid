package com.example.instareport.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.instareport.R

import kotlinx.android.synthetic.main.activity_web_view_start.*
import android.webkit.WebView
import android.webkit.WebViewClient
import android.graphics.Bitmap
import android.widget.Toast

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.google.gson.JsonObject
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.example.instareport.models.InstaAnswer
import com.example.instareport.models.ResultAnswer
import java.net.URI


class WebViewStart :  AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view_start)
        setSupportActionBar(toolbar)

        webView.loadUrl("www.instagram.com/accounts/login")


        webView.webViewClient = object : WebViewClient() {
            private var running = 0 // Could be public if you want a timer to check.

            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap) {
                super.onPageStarted(view, url, favicon)
                running = running.coerceAtLeast(1)
                //SHOW LOADING IF IT ISNT ALREADY VISIBLE
            }

            override fun onPageFinished(view: WebView, url: String) {
                // do your stuff here
                if(--running == 0) { // just "running--;" if you add a timer.
                    //finish
                }
                if(url == "www.instagram.com"){

                    showToast("good")
                    finish()
                }
            }
        }

        var uri = URI(
            "http"
        )
        var url = uri.toURL()

        ApiUtils.apiGet.registrationPost("","","","").enqueue(object:
            Callback<InstaAnswer> {

            override fun onResponse(call: Call<InstaAnswer>, response: Response<InstaAnswer>) {
                val post =  response.body()!!
                post.data.user.edge_owner_to_timeline_media.page_info.end_cursor

            }

            override fun onFailure(call: Call<InstaAnswer>, t: Throwable) {

            }
        })




    }

    private fun showToast(message: String) {
        Toast.makeText(this, message,Toast.LENGTH_SHORT).show()
    }

}
