package kr.co.americano.funco.network

import android.content.Context
import android.util.Log
import kr.co.americano.funco.view.activity.LoginActivity
import okhttp3.Interceptor

class TokenInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {

        val context: Context = MyApp.instance
        val sharedPref = context.getSharedPreferences(LoginActivity.TOKEN_PREFERENCE, Context.MODE_PRIVATE)
        val token = sharedPref.getString("token", "") ?: ""
        Log.e("TESTTEST", "token $token")
        val request = chain.request().newBuilder()
            .header("authorization", "Bearer $token")
            .build()
        return chain.proceed(request)
    }
}