package com.dohun.kinda.android.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.dohun.kinda.android.KindaViewModel
import com.dohun.kinda.core.concept.KindaEvent
import com.dohun.kinda.core.concept.KindaSideEffect
import com.dohun.kinda.core.concept.KindaState
import com.dohun.kinda.core.concept.KindaViewEffect

abstract class KindaActivity<S : KindaState, E : KindaEvent, SE : KindaSideEffect, VE : KindaViewEffect, VIEW : ViewDataBinding> :
    AppCompatActivity() {

    abstract val layoutResourceId: Int
    abstract val viewModel: KindaViewModel<S, E, SE, VE>

    open fun onStateChanged(state: S) {
    }

    open fun onViewEffect(viewEffect: VE) {
    }

    lateinit var binding: VIEW

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResourceId)
        binding.lifecycleOwner = this

        viewModel.currentState.observe(this, Observer {
            onStateChanged(it)
        })

        viewModel.viewEffect.observe(this, Observer {
            it?.let { onViewEffect(it) }
        })
    }
}