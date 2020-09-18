package dohun.kim.kinda.kinda_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import dohun.kim.kinda.kinda_core.KindaEvent
import dohun.kim.kinda.kinda_core.KindaSideEffect
import dohun.kim.kinda.kinda_core.KindaState

abstract class KindaActivity<S : KindaState, E : KindaEvent, SE : KindaSideEffect>
    : AppCompatActivity() {

    abstract val viewModel: KindaViewModel<S, E, SE>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.stateLiveData.observe(this, Observer { state ->
            render(state)
        })
    }

    abstract fun render(state: S)
}