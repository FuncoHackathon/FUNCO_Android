package kr.co.americano.funco.network.api

import kr.co.americano.funco.network.request.LoginRequest
import kr.co.americano.funco.network.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface Login {
    @POST("users/login")
    fun login(@Body loginRequest: LoginRequest) : retrofit2.Call<LoginResponse>

}