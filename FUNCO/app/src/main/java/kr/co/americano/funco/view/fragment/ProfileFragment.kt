package kr.co.americano.funco.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import kr.co.americano.funco.R
import kr.co.americano.funco.databinding.ActivityMainBinding
import kr.co.americano.funco.databinding.FragmentHomeBinding
import kr.co.americano.funco.databinding.FragmentProfileBinding
import kr.co.americano.funco.viewmodel.fragment.HomeViewModel
import kr.co.americano.funco.viewmodel.fragment.ProfileViewModel

class ProfileFragment : Fragment() {
    lateinit var binding: FragmentProfileBinding
    lateinit var profileViewModel: ProfileViewModel

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

        with(profileViewModel) {
            onPostEvent.observe(this@ProfileFragment, {
                findNavController().navigate(R.id.action_ProfileFragment_to_postFragment)
            })
        }
        return binding.root
    }

    private fun performViewModel() {
        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        binding.vm = profileViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}