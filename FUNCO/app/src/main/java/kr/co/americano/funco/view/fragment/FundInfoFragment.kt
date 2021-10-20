package kr.co.americano.funco.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import kr.co.americano.funco.R
import kr.co.americano.funco.databinding.*
import kr.co.americano.funco.viewmodel.fragment.FundInfoViewModel
import kr.co.americano.funco.viewmodel.fragment.FundRankViewModel
import kr.co.americano.funco.viewmodel.fragment.HomeViewModel

class FundInfoFragment : Fragment() {
    lateinit var binding: FragmentFundInfoBinding
    lateinit var fundInfoViewModel: FundInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentFundInfoBinding>(
            inflater,
            R.layout.fragment_fund_info,
            container,
            false
        )

        performViewModel()
        return binding.root
    }

    private fun performViewModel() {
        fundInfoViewModel = ViewModelProvider(this).get(FundInfoViewModel::class.java)
        binding.vm = fundInfoViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}