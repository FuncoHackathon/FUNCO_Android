package kr.co.americano.funco.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.co.americano.funco.R
import kr.co.americano.funco.databinding.RecyclerFundInfoItemBinding
import kr.co.americano.funco.network.model.FundInfo

class RecyclerFundInfoAdapter(val lifecycleOwner: LifecycleOwner):
    RecyclerView.Adapter<RecyclerFundInfoAdapter.FundInfoViewHolder>() {

    var fundInfoList : List<FundInfo> = ArrayList<FundInfo>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int): FundInfoViewHolder {
        return FundInfoViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.recycler_fund_info_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FundInfoViewHolder, position: Int) {
        holder.bind(fundInfoList[position])
    }

    override fun getItemCount(): Int = fundInfoList.size

    class FundInfoViewHolder(private val binding: RecyclerFundInfoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(fundInfo: FundInfo) {
            with(fundInfo) {
                binding.tvFundName.text = title
                binding.tvFundComplete.text = complete

                Glide.with(binding.root)
                    .load(image)
                    .error(R.drawable.ic_no_image)
                    .into(binding.ivFundInfo)
            }
        }
    }
}