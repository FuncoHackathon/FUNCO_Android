package kr.co.americano.funco.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import kr.co.americano.funco.R
import kr.co.americano.funco.databinding.ActivityMainBinding
import kr.co.americano.funco.databinding.FragmentHomeBinding
import kr.co.americano.funco.databinding.FragmentProfileBinding
import kr.co.americano.funco.viewmodel.fragment.HomeViewModel
import kr.co.americano.funco.viewmodel.fragment.ProfileViewModel

class ProfileFragment : Fragment() {
    lateinit var binding: FragmentProfileBinding
    lateinit var homeViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentProfileBinding>(
            inflater,
            R.layout.fragment_profile,
            container,
            false
        )

        performViewModel()
        return binding.root
    }

    private fun performViewModel() {
        homeViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        binding.vm = homeViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}