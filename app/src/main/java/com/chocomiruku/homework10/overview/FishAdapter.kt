package com.chocomiruku.homework10.overview

import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chocomiruku.homework10.databinding.ListItemFishBinding
import com.chocomiruku.homework10.domain.Fish

class FishAdapter() :
    ListAdapter<Fish, FishAdapter.ViewHolder>(FactDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fish = getItem(position)
        holder.bind(fish)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: ListItemFishBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(fish: Fish) {
            binding.fish = fish
            binding.biologyText.text =
                HtmlCompat.fromHtml(fish.biology, HtmlCompat.FROM_HTML_MODE_LEGACY)
            binding.biologyText.movementMethod = LinkMovementMethod.getInstance()
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