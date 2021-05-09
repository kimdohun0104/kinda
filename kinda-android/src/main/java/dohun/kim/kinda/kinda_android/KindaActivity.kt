package dohun.kim.kinda.kinda_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dohun.kim.kinda.kinda_core.KindaEvent
import dohun.kim.kinda.kinda_core.KindaSideEffect
import dohun.kim.kinda.kinda_core.KindaState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class KindaActivity<S : KindaState, E : KindaEvent, SE : KindaSideEffect> : AppCompatActivity() {

    abstract val viewModel: KindaViewModel<S, E, SE>

    private val job = SupervisorJob()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + job)

    abstract fun render(state: S)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        coroutineScope.launch {
            viewModel.state.collect { state ->
                render(state)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}