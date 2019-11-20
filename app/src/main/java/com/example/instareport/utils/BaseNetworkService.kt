

import com.example.instareport.models.InstaAnswer
import com.example.instareport.models.ResultAnswer
import retrofit2.Call
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.http.*
import okhttp3.OkHttpClient
import java.net.URI
import java.util.concurrent.TimeUnit


interface InstaGet {

    @GET("")
    fun registrationPost(@Query("query_hash") query_hash: String,
                         @Query("id") id: String,
                         @Query("first") first: String,
                         after: String): Call<InstaAnswer>
}

interface APIRegLogin {

    @POST("login")
    @FormUrlEncoded
    fun loginPost(@Field("login") email: String,
                  @Field("password") password: String): Call<ResultAnswer>
}

interface APIUserReSave {

    @POST("user")
    @FormUrlEncoded
    fun saveUserPost(@Body user: User): Call<ResultAnswer>
}


object ApiUtils {

    private var BASE_URL = "https://www.instagram.com/graphql/query/"



    val apiGet  = BaseRetrofit.getClient(BASE_URL).create(InstaGet::class.java)

    val apiLogin  = BaseRetrofit.getClient("$BASE_URL/login").create(APIRegLogin::class.java)

    val apiReSaveUserData: APIUserReSave = BaseRetrofit.getClient("$BASE_URL/user").create(APIUserReSave::class.java)


}

object BaseRetrofit{

    private val client = OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.SECONDS)
        //.addInterceptor(interceptor)
        .readTimeout(5, TimeUnit.SECONDS)
        .build()

    fun getClient(url: String): Retrofit {

            //val interceptor = HttpLoggingInterceptor()
            //interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return Retrofit.Builder()
            .client(client)
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

}