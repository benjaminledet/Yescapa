package com.benjaminledet.yescapa.vehicles.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.benjaminledet.core.data.local.model.Vehicle
import com.benjaminledet.core.extension.onSafeClick
import com.benjaminledet.core.utils.StarsAdapter
import com.benjaminledet.yescapa.R
import com.benjaminledet.yescapa.databinding.ItemVehicleBinding
import com.squareup.picasso.Picasso

class VehiclesAdapter: PagingDataAdapter<Vehicle, VehiclesAdapter.VehicleViewHolder>(DIFF_UTIL) {

    var onClick: ((item: Vehicle, viewsToAnimate: List<View>) -> Unit)? = null
    var onLongClick: ((item: Vehicle, viewsToAnimate: List<View>) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder = VehicleViewHolder(ItemVehicleBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class VehicleViewHolder(private val binding: ItemVehicleBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Vehicle?) {
            item?.let { vehicle ->
                binding.apply {
                    name.text = vehicle.title
                    price.text = binding.root.context.getString(R.string.price, vehicle.price)
                    location.text = vehicle.location
                    reviewCount.text = vehicle.reviewCount.toString()
                    Picasso.get().load(vehicle.pictureUrls.firstOrNull()).into(picture)
                    starsRecyclerView.apply {
                        layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
                        adapter = StarsAdapter().apply {
                            submitList(vehicle.reviewAverageToIntList())
                        }
                    }
                }

                binding.root.onSafeClick {
                    onClick?.invoke(item, listOf(binding.picture, binding.name))
                }
                binding.root.setOnLongClickListener {
                    onLongClick?.invoke(item, listOf())
                    true
                }
            }
        }
    }

    companion object {

        val DIFF_UTIL = object: DiffUtil.ItemCallback<Vehicle>() {

            override fun areItemsTheSame(oldItem: Vehicle, newItem: Vehicle): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Vehicle, newItem: Vehicle): Boolean = oldItem == newItem

        }

    }
}