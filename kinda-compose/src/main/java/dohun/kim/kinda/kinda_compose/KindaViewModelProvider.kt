package dohun.kim.kinda.kinda_compose

import androidx.compose.runtime.Composable
import dohun.kim.kinda.kinda_android.KindaViewModel
import dohun.kim.kinda.kinda_core.KindaState

val KindaViewModelSet: HashSet<KindaViewModelStateMatcher<*>> = hashSetOf()

@Composable
inline fun <reified S : KindaState, VM : KindaViewModel<S, *, *>> KindaViewModelProvider(
    viewModel: VM,
    child: @Composable () -> Unit
) {
    val kindaViewModelStateMatcher = KindaViewModelStateMatcher(S::class.java, viewModel)
    KindaViewModelSet.add(kindaViewModelStateMatcher)
    child()
    KindaViewModelSet.remove(kindaViewModelStateMatcher)
}