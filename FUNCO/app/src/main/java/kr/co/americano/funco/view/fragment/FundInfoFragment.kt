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
import kr.co.americano.funco.network.model.FundInfo
import kr.co.americano.funco.view.activity.MainActivity
import kr.co.americano.funco.view.adapter.RecyclerFundInfoAdapter
import kr.co.americano.funco.viewmodel.fragment.FundInfoViewModel
import kr.co.americano.funco.viewmodel.fragment.FundRankViewModel
import kr.co.americano.funco.viewmodel.fragment.HomeViewModel

class FundInfoFragment : Fragment() {
    lateinit var binding: FragmentFundInfoBinding
    lateinit var fundInfoViewModel: FundInfoViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.setNavVisible(true)
    }

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
        initRecycler()
        return binding.root
    }

    private fun performViewModel() {
        fundInfoViewModel = ViewModelProvider(this).get(FundInfoViewModel::class.java)
        binding.vm = fundInfoViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }

    private fun initRecycler() {
        var fundInfoList = ArrayList<FundInfo>()
        val recyclerFundInfoAdapter = RecyclerFundInfoAdapter(viewLifecycleOwner)
        binding.fundInfoRecycler.adapter = recyclerFundInfoAdapter

        fundInfoList.apply {
            add(FundInfo("https://image.msscdn.net/data/curating/16948/16948_1_org.jpg", "칫솔 칫솔 칫솔 하", "143% 달성"))
            add(FundInfo("https://image.msscdn.net/data/curating/16948/16948_1_org.jpg", "칫솔 칫솔 칫솔 하", "143% 달성"))
            add(FundInfo("https://image.msscdn.net/data/curating/16948/16948_1_org.jpg", "칫솔 칫솔 칫솔 하", "143% 달성"))
            add(FundInfo("https://image.msscdn.net/data/curating/16948/16948_1_org.jpg", "칫솔 칫솔 칫솔 하", "143% 달성"))
            add(FundInfo("https://image.msscdn.net/data/curating/16948/16948_1_org.jpg", "칫솔 칫솔 칫솔 하", "143% 달성"))
            add(FundInfo("https://image.msscdn.net/data/curating/16948/16948_1_org.jpg", "칫솔 칫솔 칫솔 하", "143% 달성"))
            add(FundInfo("https://image.msscdn.net/data/curating/16948/16948_1_org.jpg", "칫솔 칫솔 칫솔 하", "143% 달성"))
            add(FundInfo("https://image.msscdn.net/data/curating/16948/16948_1_org.jpg", "칫솔 칫솔 칫솔 하", "143% 달성"))
            add(FundInfo("https://image.msscdn.net/data/curating/16948/16948_1_org.jpg", "칫솔 칫솔 칫솔 하", "143% 달성"))
            add(FundInfo("https://image.msscdn.net/data/curating/16948/16948_1_org.jpg", "칫솔 칫솔 칫솔 하", "143% 달성"))
        }

        recyclerFundInfoAdapter.fundInfoList = fundInfoList
        recyclerFundInfoAdapter.notifyDataSetChanged()
    }
}