package dohun.kim.kinda.kinda_android

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import dohun.kim.kinda.kinda_core.KindaEvent
import dohun.kim.kinda.kinda_core.KindaSideEffect
import dohun.kim.kinda.kinda_core.KindaState

abstract class KindaFragment<S : KindaState, E : KindaEvent, SE : KindaSideEffect>
    : Fragment() {

    abstract val viewModel: KindaViewModel<S, E, SE>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.stateLiveData.observe(this, Observer { state ->
            render(state)
        })
    }

    abstract fun render(state: S)
}