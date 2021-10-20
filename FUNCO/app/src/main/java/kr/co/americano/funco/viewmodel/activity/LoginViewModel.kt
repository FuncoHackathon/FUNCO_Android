package kr.co.americano.funco.viewmodel.activity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.co.americano.funco.extension.SingleLiveEvent
import kr.co.americano.funco.network.RetrofitClient
import kr.co.americano.funco.network.request.LoginRequest
import kr.co.americano.funco.network.response.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel: ViewModel() {
    val onRegisterEvent = SingleLiveEvent<Unit>()
    val onLoginEvent = SingleLiveEvent<Unit>()

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    val token = MutableLiveData<String>()

    fun onClickLogin() {
        val call = RetrofitClient.loginInterface.login (
            LoginRequest(email.value?: "",password.value ?: "")
        )
        call.enqueue(object: Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if(response.isSuccessful) {
                    Log.d("Retrofit2", "연결 성공")

                    val result = response.body()
                    token.value = result?.token

                    onLoginEvent.call()
                } else {
                    Log.d("Retrofit2", "연결 실패")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("Retrofit2", "onFailure: $t")
            }
        })
        onLoginEvent.call()
    }

    fun onClickRegister() {
        onRegisterEvent.call()
    }
}