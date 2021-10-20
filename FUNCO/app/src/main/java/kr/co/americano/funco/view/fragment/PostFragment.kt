package kr.co.americano.funco.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kr.co.americano.funco.R
import kr.co.americano.funco.databinding.*
import kr.co.americano.funco.network.model.FundInfo
import kr.co.americano.funco.view.activity.MainActivity
import kr.co.americano.funco.view.adapter.RecyclerFundInfoAdapter
import kr.co.americano.funco.viewmodel.fragment.FundInfoViewModel
import kr.co.americano.funco.viewmodel.fragment.FundRankViewModel
import kr.co.americano.funco.viewmodel.fragment.HomeViewModel
import kr.co.americano.funco.viewmodel.fragment.PostViewModel

class PostFragment : Fragment() {
    lateinit var binding: FragmentPostBinding
    lateinit var postViewModel: PostViewModel

    // 화면에 액션바와 네비게이션 바 제거
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.setNavVisible(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentPostBinding>(
            inflater,
            R.layout.fragment_post,
            container,
            false
        )
        performViewModel()

        with(postViewModel) {
            onBackEvent.observe(this@PostFragment, {
                findNavController().navigate(R.id.action_postFragment_to_ProfileFragment)
            })
        }
        return binding.root
    }

    private fun performViewModel() {
        postViewModel = ViewModelProvider(this).get(PostViewModel::class.java)
        binding.vm = postViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}