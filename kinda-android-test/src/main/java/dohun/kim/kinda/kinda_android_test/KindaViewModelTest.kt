package dohun.kim.kinda.kinda_android_test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dohun.kim.kinda.kinda_android.KindaViewModel
import dohun.kim.kinda.kinda_core.KindaEvent
import dohun.kim.kinda.kinda_core.KindaSideEffect
import dohun.kim.kinda.kinda_core.KindaState
import org.junit.Before
import org.junit.Rule
import org.mockito.MockitoAnnotations

abstract class KindaViewModelTest<S : KindaState, E : KindaEvent, SE : KindaSideEffect> {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var viewModel: KindaViewModel<S, E, SE>

    val currentState: S
        get() = viewModel.stateLiveData.getOrAwaitValue()

    abstract fun buildViewModel(): KindaViewModel<S, E, SE>

    @Before
    fun initKindaViewModelTest() {
        MockitoAnnotations.openMocks(this)
        viewModel = buildViewModel()
    }

    fun expectState(assertion: (S) -> Unit) {
        assertion.invoke(currentState)
    }

    infix fun E.expectState(assertion: (S) -> Unit) {
        viewModel.intent(this)
        Thread.sleep(50) // Waiting for the side effect result
        assertion.invoke(currentState)
    }

    infix fun Iterable<E>.expectState(assertion: (S) -> Unit) {
        this.forEach { event ->
            viewModel.intent(event)
        }
        Thread.sleep(100) // Waiting for the side effect result
        assertion.invoke(currentState)
    }
}