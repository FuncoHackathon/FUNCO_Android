package kr.co.americano.funco.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import kr.co.americano.funco.R
import kr.co.americano.funco.databinding.ActivityRegisterBinding
import kr.co.americano.funco.viewmodel.activity.LoginViewModel
import kr.co.americano.funco.viewmodel.activity.RegisterViewModel

class RegisterActivity : AppCompatActivity() {
    lateinit var binding : ActivityRegisterBinding
    lateinit var registerViewModel : RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()

        with(registerViewModel) {
            onEmptyEvent.observe(this@RegisterActivity, {
                Toast.makeText(applicationContext, "값을 전부 입력해주세요!", Toast.LENGTH_SHORT).show()
            })

            onRegisterEvent.observe(this@RegisterActivity, {
                val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                startActivity(intent)
                Toast.makeText(this@RegisterActivity, "회원가입 성공!", Toast.LENGTH_SHORT).show()
            })
            
            messages.observe(this@RegisterActivity, {
                Toast.makeText(this@RegisterActivity, "${messages.value}", Toast.LENGTH_SHORT).show()
            })
        }
    }

    private fun performDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        registerViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        binding.vm = registerViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}