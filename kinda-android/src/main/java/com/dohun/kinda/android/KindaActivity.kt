package com.dohun.kinda.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.dohun.kinda.core.KindaEvent
import com.dohun.kinda.core.KindaSideEffect
import com.dohun.kinda.core.KindaState

abstract class KindaActivity<S : KindaState, E : KindaEvent, SE : KindaSideEffect, VIEW : ViewDataBinding> :
    AppCompatActivity() {

    abstract val layoutResourceId: Int
    abstract val viewModel: KindaViewModel<S, E, SE>
    abstract fun onStateChanged(state: S)

    lateinit var binding: VIEW

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResourceId)
        binding.lifecycleOwner = this

        viewModel.currentState.observe(this, Observer {
            onStateChanged(it)
        })
    }
}