package com.dohun.kinda.android

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.dohun.kinda.core.KindaEvent
import com.dohun.kinda.core.KindaSideEffect
import com.dohun.kinda.core.KindaState

abstract class KindaFragment<S : KindaState, E : KindaEvent, SE : KindaSideEffect, VIEW : ViewDataBinding> :
    Fragment() {

    abstract val layoutResourceId: Int
    abstract val viewModel: KindaViewModel<S, E, SE>
    abstract fun onStateChanged(state: S)

    lateinit var binding: VIEW

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResourceId, container, false)
        binding.lifecycleOwner = this

        viewModel.currentState.observe(this, Observer {
            onStateChanged(it)
        })

        return binding.root
    }
}