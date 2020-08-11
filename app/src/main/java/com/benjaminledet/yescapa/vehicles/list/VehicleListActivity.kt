package com.benjaminledet.yescapa.vehicles.list

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.benjaminledet.core.extension.intentFor
import com.benjaminledet.core.extension.onSafeClick
import com.benjaminledet.yescapa.R
import com.benjaminledet.yescapa.databinding.ActivityVehicleListBinding
import com.benjaminledet.yescapa.vehicles.detail.VehicleDetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class VehicleListActivity: AppCompatActivity() {

    private val binding: ActivityVehicleListBinding by lazy {  ActivityVehicleListBinding.inflate(layoutInflater) }

    private val viewModel: VehicleListViewModel by viewModels()

    private val vehiclesAdapter = VehiclesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupRecyclerView()
        setupToolbar()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = getString(R.string.vehicles)
    }

    private fun setupRecyclerView() {
        binding.recyclerView.adapter = vehiclesAdapter

        vehiclesAdapter.apply {
            onClick = { vehicle, viewsToAnimate ->
                startActivity(intentFor<VehicleDetailActivity>("vehicleId" to vehicle.id), ActivityOptionsCompat.makeSceneTransitionAnimation(this@VehicleListActivity, *viewsToAnimate.map { Pair.create(it, it.transitionName) }.toTypedArray()).toBundle())
            }

            viewModel.vehicles.onEach {
                submitData(it)
            }.launchIn(lifecycleScope)

            addLoadStateListener { loadState ->
                binding.recyclerView.isVisible = loadState.refresh is LoadState.NotLoading
                binding.progressBar.isVisible = loadState.refresh is LoadState.Loading
                binding.retryButton.isVisible = loadState.refresh is LoadState.Error
                binding.retryDescription.isVisible = loadState.refresh is LoadState.Error
            }
        }

        binding.retryButton.onSafeClick {
            vehiclesAdapter.retry()
        }
    }
}