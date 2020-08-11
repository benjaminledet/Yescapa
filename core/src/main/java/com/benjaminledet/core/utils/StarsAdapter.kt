package com.benjaminledet.core.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.benjaminledet.core.R
import com.benjaminledet.core.databinding.ItemStarBinding

class StarsAdapter: ListAdapter<Int, StarsAdapter.StarsViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarsViewHolder = StarsViewHolder(ItemStarBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: StarsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class StarsViewHolder(private val binding: ItemStarBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Int) {
            binding.star.setImageDrawable(binding.root.context.getDrawable(when (item) {
                0 -> R.drawable.ic_baseline_star_half_24
                1 -> R.drawable.ic_baseline_star_24
                else -> R.drawable.ic_baseline_star_border_24
            }))
        }
    }

    companion object {

        val DIFF_UTIL = object: DiffUtil.ItemCallback<Int>() {

            override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean = oldItem == newItem

            override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean = oldItem == newItem

        }

    }
}