package dohun.kim.kinda.kinda_compose

import androidx.compose.runtime.Composable
import dohun.kim.kinda.kinda_android.KindaViewModel
import dohun.kim.kinda.kinda_core.KindaState

val KindaViewModelSet: HashSet<KindaViewModel<*, *, *>> = hashSetOf()

@Composable
inline fun <reified S : KindaState, VM : KindaViewModel<S, *, *>> KindaViewModelProvider(
    viewModel: VM,
    child: @Composable () -> Unit
) {
    KindaViewModelSet.add(viewModel)
    child()
    KindaViewModelSet.remove(viewModel)
}