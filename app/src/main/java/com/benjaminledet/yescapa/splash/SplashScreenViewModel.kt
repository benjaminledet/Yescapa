package com.benjaminledet.yescapa.splash

import android.app.Application
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle

class SplashScreenViewModel @ViewModelInject constructor(application: Application, @Assisted private val savedStateHandle: SavedStateHandle): AndroidViewModel(application) {

}