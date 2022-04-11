package com.chocomiruku.homework10.presentation

import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chocomiruku.homework10.databinding.ListItemFishBinding
import com.chocomiruku.homework10.domain.Fish

const val PAYLOAD_FAV_BUTTON = "payload_favourites_button"

class FishAdapter(
    private val onClickListener: OnClickListener,
    private val viewModel: FishOverviewViewModel
) :
    ListAdapter<Fish, FishAdapter.ViewHolder>(FactDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fish = getItem(position)
        holder.bind(fish, viewModel, onClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isNotEmpty()) {
            when (payloads[0]) {
                PAYLOAD_FAV_BUTTON -> {
                    val fish = getItem(position)
                    holder.rebindFish(fish)
                }
            }
        }
        super.onBindViewHolder(holder, position, payloads)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: ListItemFishBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(fish: Fish, viewModel: FishOverviewViewModel, onClickListener: OnClickListener) {
            binding.fish = fish
            binding.viewModel = viewModel

            binding.biologyText.text =
                HtmlCompat.fromHtml(fish.biology, HtmlCompat.FROM_HTML_MODE_LEGACY)
            binding.biologyText.movementMethod = LinkMovementMethod.getInstance()

            binding.updateFavouritesBtn.setOnClickListener {
                onClickListener.onClick(fish, this.adapterPosition)
            }
        }

        fun rebindFish(fish: Fish) {
            binding.fish = fish
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    ListItemFishBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }

    class OnClickListener(val clickListener: (fish: Fish, position: Int) -> Unit) {
        fun onClick(fish: Fish, position: Int) = clickListener(fish, position)
    }

    class FactDiffCallback :
        DiffUtil.ItemCallback<Fish>() {

        override fun areItemsTheSame(oldItem: Fish, newItem: Fish): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Fish, newItem: Fish): Boolean {
            return oldItem == newItem
        }
    }
}