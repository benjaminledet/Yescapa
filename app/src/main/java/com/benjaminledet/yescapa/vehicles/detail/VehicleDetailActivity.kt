package com.benjaminledet.yescapa.vehicles.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.benjaminledet.core.data.local.model.Vehicle
import com.benjaminledet.core.utils.StarsAdapter
import com.benjaminledet.yescapa.R
import com.benjaminledet.yescapa.databinding.ActivityVehicleDetailBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class VehicleDetailActivity: AppCompatActivity() {

    private val binding: ActivityVehicleDetailBinding by lazy {  ActivityVehicleDetailBinding.inflate(layoutInflater) }

    private val viewModel: VehicleDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.vehicle.onEach {
            it?.let { vehicle ->
                setupContent(vehicle)
                setupToolbar(vehicle)
            } ?: finish()
        }.launchIn(lifecycleScope)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            finishAfterTransition()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun setupContent(vehicle: Vehicle) {
        binding.apply {
            name.text = vehicle.title
            price.text = getString(R.string.price, vehicle.price)
            location.text = vehicle.location
            reviewCount.text = vehicle.reviewCount.toString()
            owner.text = when (vehicle.type) {
                Vehicle.Type.COACH_BUILT -> getString(R.string.coachBuiltOf, vehicle.owner)
                Vehicle.Type.VAN -> getString(R.string.vanOf, vehicle.owner)
            }
            Picasso.get().load(vehicle.ownerPictureUrl).into(ownerPicture)
            Picasso.get().load(vehicle.pictureUrls.firstOrNull()).into(picture)
            starsRecyclerView.apply {
                layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
                adapter = StarsAdapter().apply {
                    submitList(vehicle.reviewAverageToIntList())
                }
            }
        }
    }

    private fun setupToolbar(vehicle: Vehicle) {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title =  when (vehicle.type) {
                Vehicle.Type.COACH_BUILT -> getString(R.string.coachBuilt)
                Vehicle.Type.VAN -> getString(R.string.van)
            }
        }
    }
}