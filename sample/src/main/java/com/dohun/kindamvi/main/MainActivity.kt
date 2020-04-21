package com.dohun.kindamvi.main

import android.content.Intent
import android.os.Bundle
import com.dohun.kinda.android.KindaActivity
import com.dohun.kindamvi.R
import com.dohun.kindamvi.databinding.ActivityMainBinding
import com.dohun.kindamvi.github.GithubActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : KindaActivity<MainState, MainEvent, MainSideEffect, MainViewEffect, ActivityMainBinding>() {

    override val viewModel: MainViewModel = MainViewModel()
    override val layoutResourceId: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        setupNavigate()
    }

    private fun setupNavigate() {
        iv_person.setOnClickListener { startActivity(Intent(this, GithubActivity::class.java)) }
    }
}
