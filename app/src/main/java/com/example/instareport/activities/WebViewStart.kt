package com.example.instareport.activities

import Network
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.webkit.CookieManager
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.instareport.R
import com.example.instareport.models.answers.AnswerOnActionFollow
import com.example.instareport.utils.SessionManager
import kotlinx.android.synthetic.main.activity_web_view_start.*
import okhttp3.ResponseBody
import org.jsoup.Jsoup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URI


class WebViewStart :  AppCompatActivity() {


    private var sessionManager: SessionManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view_start)
   //     setSupportActionBar(toolbar)


        initWebView()
        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                if(!url?.contains("instagram")!!)
                    view?.loadUrl("https://www.instagram.com")
            }

            override fun onPageFinished(view: WebView, url: String) {
                // do your stuff here
             //   if(url.contains("www.instagram.com/graphql/query/?query_hash=")) {
                    val cookies = CookieManager.getInstance().getCookie(url)
                    sessionManager = SessionManager(cookies = cookies)
                    var k = 0
                    //getFollowers(name = "durov")
                follow("22989985890")
       //         }
            }
        }


    }

    private fun initWebView() {

        webView.settings.databaseEnabled = true
        webView.clearHistory()
        webView.clearCache(true)
        webView.webChromeClient = WebChromeClient()
        webView.settings.javaScriptEnabled = true
        webView.settings.javaScriptCanOpenWindowsAutomatically = true
        webView.loadUrl("https://www.instagram.com")

    }

    private fun follow(id: String) {

        if(sessionManager == null)
            startActivityWeb()
        else
            Network.setSessionManager(manager = sessionManager!!)



        Network.instaActions.Follow(id).enqueue(object:
            Callback<AnswerOnActionFollow> {
            override fun onResponse(call: Call<AnswerOnActionFollow>, response: Response<AnswerOnActionFollow>) {
                var res = response.body()?.result
                var k =0
            }

            override fun onFailure(call: Call<AnswerOnActionFollow>, t: Throwable) {
                val mess = t.message
                ShowError(mess?.first().toString())
                return
            }
        })

    }

    private fun getFollowers(name: String) {

        if(sessionManager == null)
            startActivityWeb()
        else
            Network.setSessionManager(manager = sessionManager!!)



        Network.instaGraphql.getHtmlFromName(name).enqueue(object:
            Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val post =  Jsoup.parse(response.body()?.string())

                val allElem = post.body().allElements
                val urlConsumer = allElem.eachAttr("src").find { it.contains("Consumer.js") }?.split('/')?.last()!!
                val urlPPC = allElem.eachAttr("src").find { it.contains("ProfilePageContainer.js") }?.split('/')?.last()!!
                val hash = allElem[0].data().substringBefore("""",queryParams:t=>({id:t}),onUpdate(t,n,l)""" ).substringAfterLast(""""""")

                var endCursor = allElem[0].data().substringAfter("""end_cursor":"""" ).substringBefore(""""},"""")
                val profileId =allElem[0].data().substringAfter("profilePage_" ).substringBefore(""""""")


                Network.instaGet.getConsumerJS(urlConsumer).enqueue(object:
                    Callback<ResponseBody> {
                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                        val listJS =  response.body()?.string()
                        val queryHash =  listJS?.substringBefore("""",n="""")?.substringAfterLast("""t="""")!!
                        //{"id":"4663052","include_reel":true,"fetch_mutual":true,"first":24}
                        var t = 0
                        var variables = """{"id":"$profileId","include_reel":true,"fetch_mutual":true,"first":24}"""
                        //     "}


                        Network.instaGraphql.getFollowers(queryHash, variables).enqueue(object:
                            Callback<ResponseBody> {
                            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                                val strFoll =  response.body()?.string()
                                endCursor =  listJS.substringAfter(""""end_cursor":"""").substringBefore(""""""")
                                //       var hash = strContainsHash

                                variables = """{"id":"$profileId"","include_reel":true,"fetch_mutual":false,"first":12,"after":"$endCursor"}"""
                                Network.instaGraphql.getFollowers(queryHash, variables).enqueue(object:
                                    Callback<ResponseBody> {
                                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                                        val fol =  response.body()?.string()
                                        val strContainsFoll =  fol?.substringBefore("""",n="""")?.substringAfterLast("""t="""")
                                        //       var hash = strContainsHash

                                        var t = 0
                                    }

                                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                                        val mess = t.message
                                        ShowError(mess?.first().toString())
                                        return
                                    }
                                })
                                var t = 0
                            }

                            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                                val mess = t.message
                                ShowError(mess?.first().toString())
                                return
                            }
                        })
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        val mess = t.message
                        ShowError(mess?.first().toString())
                        return
                    }
                })

                //https://www.instagram.com/static/bundles/es6/ProfilePageContainer.js/7e24b0f99871.js

                //",queryParams:t=>({id:t}),onUpdate(t,n,l)

            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                val mess = t.message
                ShowError(mess?.first().toString())
                return
            }
        })



    }

    private fun startActivityWeb() {
//        val intent = Intent(this, WebViewStart::class.java)
//        startActivity(intent)

    }

    private fun ShowError(err:String) {
        Toast.makeText(this,err,Toast.LENGTH_SHORT).show()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message,Toast.LENGTH_SHORT).show()
    }

}
