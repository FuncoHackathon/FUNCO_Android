package kr.co.americano.funco.viewmodel.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.co.americano.funco.extension.SingleLiveEvent

class RegisterViewModel: ViewModel() {
    val onRegisterEvent = SingleLiveEvent<Unit>()

    val name = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val checkPassword = MutableLiveData<String>()

    fun onClickRegister() {
        if ()
        onRegisterEvent.call()
    }
}