package kr.co.americano.funco.network.api

import kr.co.americano.funco.network.request.SecondPostingRequest
import kr.co.americano.funco.network.response.FirstPostingResponse
import kr.co.americano.funco.network.response.SecondPostingResponse
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface Posting {
    @Multipart
    @POST("fundings/upload/img")
    fun firstPosting(@Part img: List<MultipartBody.Part>?) : retrofit2.Call<FirstPostingResponse>

    @POST("fundings/upload")
    fun secondPosting(@Body secondPostingRequest: SecondPostingRequest) : retrofit2.Call<SecondPostingResponse>
}