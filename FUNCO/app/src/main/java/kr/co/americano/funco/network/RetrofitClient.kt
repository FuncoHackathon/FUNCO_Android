package kr.co.americano.funco.network

import com.google.gson.GsonBuilder
import kr.co.americano.funco.network.api.Login
import kr.co.americano.funco.network.api.Posting
import kr.co.americano.funco.network.api.Register
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private const val BASE_URL = "http://10.80.163.231:4000/"

    val registerInterface: Register
    val loginInterface: Login
    val postingInterface: Posting
    init {
        val gson = GsonBuilder().setLenient().create()

        val intercepter = HttpLoggingInterceptor()
        intercepter.level = HttpLoggingInterceptor.Level.BODY

        val logger =
            OkHttpClient.Builder().addInterceptor(intercepter).addInterceptor(TokenInterceptor())
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS)
                .build()

        val instance = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(logger)
            .build()

        registerInterface = instance.create(Register::class.java)
        loginInterface = instance.create(Login::class.java)
        postingInterface = instance.create(Posting::class.java)
    }
}