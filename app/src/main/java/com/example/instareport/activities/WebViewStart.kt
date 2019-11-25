package com.example.instareport.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.instareport.R

import kotlinx.android.synthetic.main.activity_web_view_start.*
import android.webkit.WebView
import android.webkit.WebViewClient
import android.graphics.Bitmap
import android.text.Html
import android.util.JsonReader
import android.widget.Toast
import com.example.instareport.models.answers.FirstNameJson
import okhttp3.ResponseBody
import org.jsoup.Jsoup
import org.jsoup.select.Elements

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import java.net.URI


class WebViewStart :  AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view_start)
        setSupportActionBar(toolbar)

//        webView.loadUrl("www.instagram.com/accounts/login")
//
//
//        webView.webViewClient = object : WebViewClient() {
//            private var running = 0 // Could be public if you want a timer to check.
//
//            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap) {
//                super.onPageStarted(view, url, favicon)
//                running = running.coerceAtLeast(1)
//                //SHOW LOADING IF IT ISNT ALREADY VISIBLE
//            }
//
//            override fun onPageFinished(view: WebView, url: String) {
//                // do your stuff here
//                if(--running == 0) { // just "running--;" if you add a timer.
//                    //finish
//                }
//                if(url == "www.instagram.com"){
//
//                    showToast("good")
//                    finish()
//                }
//            }
//        }

        var uri = URI(
            "http"
        )
//        var url = uri.toURL()

        getFollowers()





    }

    private fun getFollowers() {
        Network.instaGraphql.getFirst("durov").enqueue(object:
            Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val post =  Jsoup.parse(response.body()?.string())

                val allElem = post.body().allElements
                var urlConsumer = allElem.eachAttr("src").find { it.contains("Consumer.js") }
                val end_cursor = allElem[0].data().substringAfter("""end_cursor":"""" ).substringBefore(""""},"""")


                var endCursor = 0
                var k =0
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                val mess = t.message
                ShowError(mess?.first().toString())
                return
            }
        })

        Network.instaGraphql.getFirstJson("durov").enqueue(object:
            Callback<FirstNameJson> {
            override fun onResponse(call: Call<FirstNameJson>, response: Response<FirstNameJson>) {
                val post =  response.body()!!
                var end_curs = post.graphql.user.edge_owner_to_timeline_media.page_info.end_cursor

            }

            override fun onFailure(call: Call<FirstNameJson>, t: Throwable) {
                val mess = t.message
                ShowError(mess?.first().toString())
                return
            }
        })

    }

    private fun ShowError(err:String) {
        Toast.makeText(this,err,Toast.LENGTH_SHORT).show()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message,Toast.LENGTH_SHORT).show()
    }

}
