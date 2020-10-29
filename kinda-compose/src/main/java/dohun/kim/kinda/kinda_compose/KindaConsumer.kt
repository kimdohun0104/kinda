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
    val stateMatcher = KindaViewModelSet.find { it.match<S>() }
    if (stateMatcher?.viewModel == null) {
        throw IllegalStateException("[${S::class.java.simpleName}] 타입의 KindaViewModelProvider가 존재하지 않습니다")
    }

    val viewModel = stateMatcher.viewModel as KindaViewModel<S, KindaEvent, KindaSideEffect>
    val state = viewModel.stateLiveData.observeAsState().value
    child(viewModel, state!!)
}