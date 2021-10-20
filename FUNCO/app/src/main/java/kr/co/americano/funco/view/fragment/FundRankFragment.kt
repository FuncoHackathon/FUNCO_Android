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
import kr.co.americano.funco.databinding.FragmentFundRankBinding
import kr.co.americano.funco.databinding.FragmentHomeBinding
import kr.co.americano.funco.databinding.FragmentSearchBinding
import kr.co.americano.funco.view.activity.MainActivity
import kr.co.americano.funco.viewmodel.fragment.FundRankViewModel
import kr.co.americano.funco.viewmodel.fragment.HomeViewModel

class FundRankFragment : Fragment() {
    lateinit var binding: FragmentFundRankBinding
    lateinit var fundRankViewModel: FundRankViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.setNavVisible(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentFundRankBinding>(
            inflater,
            R.layout.fragment_fund_rank,
            container,
            false
        )

        performViewModel()
        return binding.root
    }

    private fun performViewModel() {
        fundRankViewModel = ViewModelProvider(this).get(FundRankViewModel::class.java)
        binding.vm = fundRankViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}