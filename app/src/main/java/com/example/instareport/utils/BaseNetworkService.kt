

import android.text.Html
import com.example.instareport.models.answers.FirstNameJson
import retrofit2.Call
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.http.*
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import java.net.URI
import java.util.concurrent.TimeUnit


interface InstaGet {

    @GET("query")
    fun getFromHash(@Query("query_hash") query_hash: String,
                         @Query("variables") variables: String): Call<String>

    @GET("/{name}/?__a=1")
    fun getFirstJson(@Path("name") name: String): Call<FirstNameJson>


    @GET("/{name}")
    fun getFirst(@Path("name") name: String): Call<ResponseBody>
}

interface APIRegLogin {

    @POST("login")
    @FormUrlEncoded
    fun loginPost(@Field("login") email: String,
                  @Field("password") password: String): Call<String>
}



object Network {

    private var BASE_URL_Qraphql = "https://www.instagram.com/graphql/"
    private var BASE_URL = "https://www.instagram.com"



    val apiGet  = BaseRetrofit.getClient(BASE_URL).create(InstaGet::class.java)

    val instaGraphql  = BaseRetrofit.getClient(BASE_URL_Qraphql).create(InstaGet::class.java)

//    val apiLogin  = BaseRetrofit.getClient(BASE_URL + "login").create(APIRegLogin::class.java)

}

object BaseRetrofit{
    private val interceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    private val client = OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
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