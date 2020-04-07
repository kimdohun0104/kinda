package com.dohun.kindamvi.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dohun.kinda.android.KindaActivity
import com.dohun.kinda.android.KindaViewModel
import com.dohun.kindamvi.R
import com.dohun.kindamvi.databinding.ActivityMainBinding

class MainActivity : KindaActivity<MainState, MainEvent, MainSideEffect, ActivityMainBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
    }

    override val viewModel: MainViewModel = MainViewModel()

    override val layoutResourceId: Int = R.layout.activity_main

    override fun onStateChanged(state: MainState) {
        binding.tvCount.text = state.count.toString()
    }
}
