package dohun.kim.kinda.kinda_compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import dohun.kim.kinda.kinda_android.KindaViewModel
import dohun.kim.kinda.kinda_core.KindaEvent
import dohun.kim.kinda.kinda_core.KindaSideEffect
import dohun.kim.kinda.kinda_core.KindaState

class KindaViewModelStateMatcher<S : KindaState>(
    val stateClazz: Class<S>,
    val viewModel: KindaViewModel<S, *, *>
) {
    inline fun <reified STATE : KindaState> match(): Boolean {
        return stateClazz == STATE::class.java
    }
}