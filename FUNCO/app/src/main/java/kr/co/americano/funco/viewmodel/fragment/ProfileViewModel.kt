package kr.co.americano.funco.viewmodel.fragment

import androidx.lifecycle.ViewModel
import kr.co.americano.funco.extension.SingleLiveEvent

class ProfileViewModel: ViewModel() {
    val onPostEvent = SingleLiveEvent<Unit>()

    fun onClickAddPost() {
        onPostEvent.call()
    }
}