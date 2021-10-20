package kr.co.americano.funco.viewmodel.activity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.co.americano.funco.extension.SingleLiveEvent
import kr.co.americano.funco.network.RetrofitClient
import kr.co.americano.funco.network.request.RegisterRequest
import kr.co.americano.funco.network.response.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel: ViewModel() {
    val onRegisterEvent = SingleLiveEvent<Unit>()
    val onEmptyEvent = SingleLiveEvent<Unit>()

    val name = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val checkPassword = MutableLiveData<String>()

    var messages = MutableLiveData<String>()

    fun onClickRegister() {
        if (name.value != null && email.value != null && password.value != null && checkPassword.value != null) {
            val registerRequest = RegisterRequest(
                email.value ?: "",
                password.value ?: "",
                checkPassword.value ?: "",
                name.value ?: ""
            )
            RetrofitClient.registerInterface.register(registerRequest)
                .enqueue(object : Callback<RegisterResponse> {
                    override fun onResponse(
                        call: Call<RegisterResponse>,
                        response: Response<RegisterResponse>
                    ) {
                        val result = response.body()
                        if (response.isSuccessful) {
                            onRegisterEvent.call()
                            Log.d("Retrofit2", "onResponse: 성공")
                        } else {
                            messages.value = result?.message ?: ""
                            Log.d("Retrofit2", "onResponse: ${response.errorBody()}")
                        }
                    }

                    override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                        messages.value = "서버 오류"
                        Log.d("Retrofit2", "onFailure: $t")
                    }

                })
        } else {
            onEmptyEvent.call()
        }
    }
}