package kr.co.americano.funco.view.fragment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import kr.co.americano.funco.R
import kr.co.americano.funco.databinding.*
import kr.co.americano.funco.network.model.FundInfo
import kr.co.americano.funco.view.activity.MainActivity
import kr.co.americano.funco.view.adapter.RecyclerFundInfoAdapter
import kr.co.americano.funco.viewmodel.fragment.FundInfoViewModel
import kr.co.americano.funco.viewmodel.fragment.FundRankViewModel
import kr.co.americano.funco.viewmodel.fragment.HomeViewModel
import kr.co.americano.funco.viewmodel.fragment.PostViewModel
import java.io.File

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

            onAddImageEvent.observe(this@PostFragment, {
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                intent.type = "image/*"
                startActivityForResult(intent, 10)
            })

            onPostingEvent.observe(this@PostFragment, {
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 10 && resultCode == Activity.RESULT_OK) {
            data?.data?.let { uri ->
                val imageUri: Uri? = data.data
                postViewModel.img.value?.add( File(imageUri?.let { getRealPathFromURI(it).path }))
                if (imageUri != null) {
                    Glide.with(binding.root)
                        .load(imageUri)
                        .error(R.drawable.ic_no_image)
                        .centerCrop()
                        .into(binding.ivMainImage)
                }
            }
        }
    }

    private fun getRealPathFromURI(uri: Uri): Uri {
        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = context?.contentResolver?.query(uri, filePathColumn, null, null, null)
        cursor?.moveToFirst()
        val columnIndex = cursor?.getColumnIndex(filePathColumn[0])
        val picturePath = columnIndex?.let { cursor.getString(it) }
        cursor?.close()
        return Uri.fromFile(File(picturePath ?: ""))
    }
}