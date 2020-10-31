package dohun.kim.kinda.kinda_compose

import androidx.compose.runtime.Composable
import dohun.kim.kinda.kinda_android.KindaViewModel
import dohun.kim.kinda.kinda_core.KindaState

val KindaViewModelSet: HashSet<KindaViewModel<*, *, *>> = hashSetOf()

@Composable
inline fun KindaViewModelProvider(
    viewModel: KindaViewModel<*, *, *>,
    child: @Composable () -> Unit
) {
    KindaViewModelSet.add(viewModel)
    child()
    KindaViewModelSet.remove(viewModel)
}

@Composable
inline fun MultipleKindaViewModelProvider(
    vararg viewModels: KindaViewModel<*, *, *>,
    child: @Composable () -> Unit
) {
    KindaViewModelSet.addAll(viewModels)
    child()
    KindaViewModelSet.removeAll(viewModels)
}