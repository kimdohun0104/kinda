package com.dohun.kindamvi.github

import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dohun.kinda.android.KindaActivity
import com.dohun.kindamvi.R
import com.dohun.kindamvi.data.GithubApi
import com.dohun.kindamvi.databinding.ActivityGithubBinding
import com.dohun.kindamvi.github.adapter.UserListAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_github.*

class GithubActivity :
    KindaActivity<GithubState, GithubEvent, GithubSideEffect, GithubViewEffect, ActivityGithubBinding>() {

    override val layoutResourceId: Int = R.layout.activity_github
    override val viewModel: GithubViewModel = GithubViewModel(GithubApi.getGithubApi())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        setupRecyclerView()
        setupNavigate()
    }

    override fun onViewEffect(viewEffect: GithubViewEffect) {
        when (viewEffect) {
            is GithubViewEffect.ShowSnackbar ->
                Snackbar.make(cl_parent, viewEffect.message, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun setupRecyclerView() {
        rv_user.adapter = UserListAdapter()
        rv_user.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
    }

    private fun setupNavigate() {
        tb_github.setNavigationOnClickListener { finish() }
    }
}