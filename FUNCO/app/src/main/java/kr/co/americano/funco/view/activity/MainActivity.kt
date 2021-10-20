package kr.co.americano.funco.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import kr.co.americano.funco.R
import kr.co.americano.funco.databinding.ActivityMainBinding
import kr.co.americano.funco.viewmodel.activity.MainViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
        setBottomNav()
    }

    private fun performDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.vm = mainViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }

    private fun setBottomNav() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val navController = navHostFragment.navController
        binding.bnvMain
            .setupWithNavController(navController)
    }

    fun setNavVisible(isMainFragment: Boolean) {
        binding.appbarHome.visibility = if(isMainFragment) View.VISIBLE else View.GONE
        binding.bnvMain.visibility = if(isMainFragment) View.VISIBLE else View.GONE
    }

    fun setAppBarVisible(isSearchFragment: Boolean) {
        binding.appbarHome.visibility = if(isSearchFragment) View.GONE else View.VISIBLE
        binding.bnvMain.visibility = if (isSearchFragment) View.VISIBLE else View.VISIBLE
    }
}