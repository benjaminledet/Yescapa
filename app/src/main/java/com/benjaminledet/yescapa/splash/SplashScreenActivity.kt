package com.benjaminledet.yescapa.splash

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.benjaminledet.core.extension.intentFor
import com.benjaminledet.yescapa.databinding.ActivitySplashScreenBinding
import com.benjaminledet.yescapa.vehicles.list.VehicleListActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreenActivity: AppCompatActivity() {

    private val binding: ActivitySplashScreenBinding by lazy { ActivitySplashScreenBinding.inflate(layoutInflater) }

    private val viewModel: SplashScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        startActivity(intentFor<VehicleListActivity>())
        finish()
    }
}