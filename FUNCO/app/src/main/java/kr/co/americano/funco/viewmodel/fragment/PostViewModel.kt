package kr.co.americano.funco.viewmodel.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.co.americano.funco.extension.SingleLiveEvent
import kr.co.americano.funco.network.RetrofitClient
import kr.co.americano.funco.network.response.FirstPostingResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


class PostViewModel: ViewModel() {
    val onBackEvent = SingleLiveEvent<Unit>()
    val onAddImageEvent = SingleLiveEvent<Unit>()
    val onPostingEvent = SingleLiveEvent<Unit>()

    var img = MutableLiveData<ArrayList<File>>(arrayListOf())


    fun onClickBack() {
        onBackEvent.call()
    }

    fun onClickAddImage() {
        onAddImageEvent.call()
    }

    fun onClickPosting() {

        val firstCall = RetrofitClient.postingInterface.firstPosting(
            img.value?.map { MultipartBody.Part.createFormData(
                "img",
                it.name,
                RequestBody.create("image/${it.name.split(".")[1]}".toMediaTypeOrNull(), it)
            )}
        )

        firstCall.enqueue(object : Callback<FirstPostingResponse> {
            override fun onResponse(
                call: Call<FirstPostingResponse>,
                response: Response<FirstPostingResponse>
            ) {
                if(response.isSuccessful) {
                    Log.d("Retrofit2", "onResponse: 성공")
                    onPostingEvent.call()
                } else {
                    Log.d("Retrofit2", "onResponse: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<FirstPostingResponse>, t: Throwable) {
                Log.d("Retrofit2", "onFailure: $t")
            }

        })
    }
}