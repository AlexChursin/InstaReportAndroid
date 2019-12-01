

import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import com.example.instareport.models.answers.AnswerOnActionFollow
import com.example.instareport.models.answers.FirstNameJson
import com.example.instareport.utils.SessionManager
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit


interface InstaGet {

    @GET("query")
    fun getFromHash(@Query("query_hash") query_hash: String,
                    @Query("variables") variables: String): Call<String>

    @GET("/{name}/?__a=1")
    fun getFirstJson(@Path("name") name: String): Call<FirstNameJson>


    @GET("/{name}")
    fun getHtmlFromName(@Path("name") name: String): Call<ResponseBody>

    @GET("static/bundles/metro/Consumer.js/{path}")
    fun getConsumerJS(@Path("path") path: String): Call<ResponseBody>

    @GET("static/bundles/metro/ProfilePageContainer.js/{path}")
    fun getProfilePageContainerJS(@Path("path") path: String): Call<ResponseBody>

    @GET("query/")
    fun getFollowers(@Query("query_hash") query_hash: String, @Query("variables") variables: String): Call<ResponseBody>
}

interface InstaActions {

    @POST("web/friendships/{id}/follow/")
    fun Follow(@Path("id") id: String): Call<AnswerOnActionFollow>

    @POST("web/friendships/{id}/unfollow/")
    fun UnFollow(@Path("id") id: String): Call<AnswerOnActionFollow>
}

interface APIRegLogin {

    @POST("login")
    @FormUrlEncoded
    fun loginPost(@Field("login") email: String,
                  @Field("password") password: String): Call<String>
}



object Network {

    private var BASE_URL_Qraphql = "https://www.instagram.com/graphql/"
    val instaGraphql  = BaseRetrofit.getClient(BASE_URL_Qraphql).create(InstaGet::class.java)
    internal var cookie = ""

    fun setSessionManager(manager: SessionManager){
        cookie = manager.getCookies()
    }



    private var BASE_URL = "https://www.instagram.com/"
    val instaGet  = BaseRetrofit.getClient(BASE_URL).create(InstaGet::class.java)
    val instaActions  = BaseRetrofit.getClient(BASE_URL).create(InstaActions::class.java)

//https://www.instagram.com/web/friendships/22989985890/follow/

    val urlConsumer  = BaseRetrofit.getClient(BASE_URL).create(InstaGet::class.java)

    //   https://www.instagram.com/static/bundles/es6/Consumer.js/e380c319f0ca.js


//    val apiLogin  = BaseRetrofit.getClient(BASE_URL + "login").create(APIRegLogin::class.java)

}

object BaseRetrofit{
    private val interceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY

    }

    private val client = OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.SECONDS)
        .addInterceptor (interceptor)
        .addNetworkInterceptor ( object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {

                val original = chain.request()

                original.newBuilder()
                    .headers(original.headers)
                    .header("cookie", Network.cookie)
                    .build()

                return chain.proceed(original)
            }

        } )
        .addInterceptor ( object: Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val request = chain.request().newBuilder().addHeader("cookie", "value").build()
                return chain.proceed(request)
            }

        } )
        //.addInterceptor(interceptor)
        .readTimeout(5, TimeUnit.SECONDS)
        .build()

    fun getClient(url: String): Retrofit {


        return Retrofit.Builder()
            .client(client)
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

}