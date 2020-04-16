package com.dohun.kindamvi.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dohun.kinda.android.KindaActivity
import com.dohun.kinda.android.KindaViewModel
import com.dohun.kindamvi.R
import com.dohun.kindamvi.databinding.ActivityMainBinding
import com.dohun.kindamvi.github.GithubActivity
import com.dohun.kindamvi.github.GithubApi
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : KindaActivity<MainState, MainEvent, MainSideEffect, Unit, ActivityMainBinding>() {

    override val viewModel: MainViewModel = MainViewModel()
    override val layoutResourceId: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        btn_github.setOnClickListener { startActivity(Intent(this, GithubActivity::class.java)) }
    }
}
