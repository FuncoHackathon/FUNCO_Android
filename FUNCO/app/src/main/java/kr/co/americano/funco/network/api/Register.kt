package kr.co.americano.funco.network.api

import kr.co.americano.funco.network.request.RegisterRequest
import kr.co.americano.funco.network.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface Register {
    @POST("users/join")
    fun register(@Body registerRequest: RegisterRequest) : retrofit2.Call<RegisterResponse>
}