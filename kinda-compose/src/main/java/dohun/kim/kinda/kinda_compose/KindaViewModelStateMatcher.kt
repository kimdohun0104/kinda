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

val KindaViewModelSet: HashSet<KindaViewModelStateMatcher<*>> = hashSetOf()

@Composable
private inline fun <reified S : KindaState, VM : KindaViewModel<S, *, *>> KindaViewModelProvider(
    viewModel: VM,
    child: @Composable () -> Unit
) {
    // TODO: 2020/10/26 이미 해당 타입에 대해 가지고 있으면 필요없음
    val kindaViewModelStateMatcher = KindaViewModelStateMatcher(S::class.java, viewModel)
    KindaViewModelSet.add(kindaViewModelStateMatcher)
    child()
    KindaViewModelSet.remove(kindaViewModelStateMatcher)
}

@Composable
inline fun <reified S : KindaState> KindaConsumer(
    child: @Composable (viewModel: KindaViewModel<S, KindaEvent, KindaSideEffect>, state: S) -> Unit
) {
    val stateMatcher = KindaViewModelSet.find { it.match<S>() }
    if (stateMatcher?.viewModel == null) {
        throw IllegalStateException()
    }

    val viewModel = stateMatcher.viewModel as KindaViewModel<S, KindaEvent, KindaSideEffect>
    val state = viewModel.stateLiveData.observeAsState().value
    child(viewModel, state!!)
}