package kr.co.americano.funco.viewmodel.fragment

import androidx.lifecycle.ViewModel
import kr.co.americano.funco.extension.SingleLiveEvent

class PostViewModel: ViewModel() {
    val onBackEvent = SingleLiveEvent<Unit>()
    val onAddImageEvent = SingleLiveEvent<Unit>()

    fun onClickBack() {
        onBackEvent.call()
    }

    fun onClickAddImage() {
        onAddImageEvent.call()
    }
}