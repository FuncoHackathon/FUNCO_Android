package kr.co.americano.funco.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import kr.co.americano.funco.R
import kr.co.americano.funco.databinding.ActivityMainBinding
import kr.co.americano.funco.databinding.FragmentHomeBinding
import kr.co.americano.funco.view.activity.MainActivity
import kr.co.americano.funco.viewmodel.fragment.HomeViewModel

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var homeViewModel: HomeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.setNavVisible(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
        performViewModel()


        // home tabLayout operate
        binding.tabMain.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val transaction = childFragmentManager.beginTransaction()
                when (tab?.text) {
                    "펀딩 랭킹" -> {
                        transaction.replace(R.id.main_tap_content, FundRankFragment())
                    }
                    "펀딩 정보" -> {
                        transaction.replace(R.id.main_tap_content, FundInfoFragment())
                    }
                }
                transaction.commit()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

        return binding.root
    }

    private fun performViewModel() {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding.vm = homeViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}