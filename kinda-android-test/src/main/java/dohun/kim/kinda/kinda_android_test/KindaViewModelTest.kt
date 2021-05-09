package dohun.kim.kinda.kinda_android_test

import android.annotation.SuppressLint
import dohun.kim.kinda.kinda_android.KindaViewModel
import dohun.kim.kinda.kinda_core.KindaEvent
import dohun.kim.kinda.kinda_core.KindaSideEffect
import dohun.kim.kinda.kinda_core.KindaState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule

@SuppressLint("NewApi")
abstract class KindaViewModelTest<S : KindaState, E : KindaEvent, SE : KindaSideEffect> {

    abstract fun buildViewModel(): KindaViewModel<S, E, SE>

    lateinit var viewModel: KindaViewModel<S, E, SE>

    protected val currentState: S
        get() = viewModel.state.value

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Before
    fun initKindaViewModelTest() {
        viewModel = buildViewModel()
    }

    infix fun E.expectState(assertion: (S) -> Unit) {
        viewModel.intent(this)
        viewModel.kinda.sideEffectPhaser.arriveAndAwaitAdvance()
        assertion(currentState)
    }

    infix fun Iterable<E>.expectState(assertion: (S) -> Unit) {
        this.forEach { event -> viewModel.intent(event) }
        viewModel.kinda.sideEffectPhaser.arriveAndAwaitAdvance()
        assertion.invoke(currentState)
    }
}