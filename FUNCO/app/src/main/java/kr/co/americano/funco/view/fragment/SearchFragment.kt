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
import kr.co.americano.funco.databinding.FragmentSearchBinding
import kr.co.americano.funco.viewmodel.fragment.HomeViewModel
import kr.co.americano.funco.viewmodel.fragment.SearchViewModel

class SearchFragment : Fragment() {
    lateinit var binding: FragmentSearchBinding
    lateinit var searchViewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentSearchBinding>(
            inflater,
            R.layout.fragment_search,
            container,
            false
        )

        performViewModel()
        return binding.root
    }

    private fun performViewModel() {
        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        binding.vm = searchViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}