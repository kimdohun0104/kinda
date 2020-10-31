package dohun.kim.kinda.kinda_compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import dohun.kim.kinda.kinda_android.KindaViewModel
import dohun.kim.kinda.kinda_core.KindaEvent
import dohun.kim.kinda.kinda_core.KindaSideEffect
import dohun.kim.kinda.kinda_core.KindaState

@Composable
inline fun <reified S : KindaState> KindaConsumer(
    child: @Composable (viewModel: KindaViewModel<S, KindaEvent, KindaSideEffect>, state: S) -> Unit
) {
    val viewModel = KindaViewModelSet.find {
        it.stateClass == S::class.java
    } as? KindaViewModel<S, KindaEvent, KindaSideEffect>
        ?: throw IllegalStateException("[${S::class.java.simpleName}] 타입의 KindaViewModelProvider가 존재하지 않습니다")

    val state = viewModel.stateLiveData.observeAsState().value
    child(viewModel, state!!)
}