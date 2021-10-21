package kr.co.americano.funco.viewmodel.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.co.americano.funco.extension.SingleLiveEvent
import kr.co.americano.funco.network.RetrofitClient
import kr.co.americano.funco.network.request.SecondPostingRequest
import kr.co.americano.funco.network.response.FirstPostingResponse
import kr.co.americano.funco.network.response.SecondPostingResponse
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

    // 첫번째 포스팅
    var img = MutableLiveData<ArrayList<File>>(arrayListOf())

    // 두번쨰 포스팅
    val title = MutableLiveData<String>()
    val goal = MutableLiveData<String>()
    val closingYear = MutableLiveData<String>()
    val closingMonth = MutableLiveData<String>()
    val closingDay = MutableLiveData<String>()
    val story = MutableLiveData<String>()
    val img2 = MutableLiveData<String>()

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

                    val firstResult = response.body()
                    img2.value = firstResult?.img

                    val secondCall = RetrofitClient.postingInterface.secondPosting(
                        SecondPostingRequest(
                            title.value ?: "",
                            goal.value!!.toInt(),
                            closingYear.value!!.toInt(),
                            closingMonth.value!!.toInt(),
                            closingDay.value!!.toInt(),
                            story.value ?: "",
                            img2.value ?: ""
                        )
                    )
                    secondCall.enqueue(object : Callback<SecondPostingResponse> {
                        override fun onResponse(
                            secondCall: Call<SecondPostingResponse>,
                            secondResponse: Response<SecondPostingResponse>
                        ) {
                            if (secondResponse.isSuccessful) {
                                Log.d("Retrofit2", "onResponse: 성공")
                                onPostingEvent.call()
                            } else {
                                Log.d("Retrofit2", "onResponse: ${secondResponse.code()}")
                            }
                        }

                        override fun onFailure(secondCall: Call<SecondPostingResponse>, t2: Throwable) {
                            Log.d("Retrofit2", "onFailure: $t2")
                        }

                    })
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