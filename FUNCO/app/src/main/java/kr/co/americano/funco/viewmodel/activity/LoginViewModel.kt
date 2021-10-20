package kr.co.americano.funco.viewmodel.activity

import androidx.lifecycle.ViewModel
import kr.co.americano.funco.extension.SingleLiveEvent

class LoginViewModel: ViewModel() {
    val onRegisterEvent = SingleLiveEvent<Unit>()

    fun onClickRegister() {
        onRegisterEvent.call()
    }
}