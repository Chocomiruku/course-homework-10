package com.chocomiruku.homework10.presentation

import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chocomiruku.homework10.R
import com.chocomiruku.homework10.databinding.ListItemFishBinding
import com.chocomiruku.homework10.domain.Fish

class FishAdapter(
    private val onClickListener: OnClickListener,
    private val viewModel: FishOverviewViewModel
) :
    ListAdapter<Fish, FishAdapter.ViewHolder>(FactDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fish = getItem(position)
        holder.bind(fish, viewModel, onClickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: ListItemFishBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(fish: Fish, viewModel: FishOverviewViewModel, onClickListener: OnClickListener) {
            binding.fish = fish
            updateFavouritesButton(binding.updateFavouritesBtn, fish, viewModel)

            binding.biologyText.text =
                HtmlCompat.fromHtml(fish.biology, HtmlCompat.FROM_HTML_MODE_LEGACY)
            binding.biologyText.movementMethod = LinkMovementMethod.getInstance()

            binding.updateFavouritesBtn.setOnClickListener {
                onClickListener.onClick(fish)
                updateFavouritesButton(it as Button, fish, viewModel)
            }
        }

        private fun updateFavouritesButton(
            it: Button,
            fish: Fish,
            viewModel: FishOverviewViewModel
        ) {
            val isAdded = viewModel.checkIsAddedToFavourites(fish)

            if (!isAdded) {
                it.setCompoundDrawablesWithIntrinsicBounds(
                    AppCompatResources.getDrawable(
                        it.context,
                        R.drawable.ic_baseline_favorite_border
                    ), null, null, null
                )
            } else {
                it.setCompoundDrawablesWithIntrinsicBounds(
                    AppCompatResources.getDrawable(
                        it.context,
                        R.drawable.ic_baseline_favorite_filled
                    ), null, null, null
                )
            }
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

    class OnClickListener(val clickListener: (fish: Fish) -> Unit) {
        fun onClick(fish: Fish) = clickListener(fish)
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