package kr.co.americano.funco.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import kr.co.americano.funco.R
import kr.co.americano.funco.databinding.ActivityLoginBinding
import kr.co.americano.funco.viewmodel.activity.LoginViewModel

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()

        with(loginViewModel) {
            onRegisterEvent.observe(this@LoginActivity, {
                val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                startActivity(intent)
            })
        }
    }

    private fun performDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.vm = loginViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}