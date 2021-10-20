package kr.co.americano.funco.view.activity

import android.content.Context
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

    companion object {
        const val TOKEN_PREFERENCE = "TOKEN_PREFERENCES"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()

        with(loginViewModel) {
            onRegisterEvent.observe(this@LoginActivity, {
                val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                startActivity(intent)
            })

            token.observe(this@LoginActivity, {
                val sharedPref = applicationContext.getSharedPreferences(TOKEN_PREFERENCE, Context.MODE_PRIVATE)

                with(sharedPref.edit()) {
                    putString("token", it)
                    apply()
                }
            })

            onLoginEvent.observe(this@LoginActivity, {
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
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