package dohun.kinda.androidwithdsl

import dohun.kim.kinda.androidwithdsl.CountEvent
import dohun.kim.kinda.androidwithdsl.CountSideEffect
import dohun.kim.kinda.androidwithdsl.CountState
import dohun.kim.kinda.androidwithdsl.CountViewModel
import dohun.kim.kinda.kinda_android.KindaViewModel
import dohun.kim.kinda.kinda_android_test.KindaViewModelTest
import org.junit.Assert.assertEquals
import org.junit.Test

class CountViewModelTest : KindaViewModelTest<CountState, CountEvent, CountSideEffect>() {

    override fun buildViewModel(): KindaViewModel<CountState, CountEvent, CountSideEffect> =
        CountViewModel()

    @Test
    fun `increase From0 Next1`() {
        CountEvent.Increase expectState {
            assertEquals(1, it.count)
        }
    }

    @Test
    fun `decrease From0 Next-1`() {
        CountEvent.Decrease expectState {
            assertEquals(-1, it.count)
        }
    }

    @Test
    fun `attemptMagic From0 Next1000`() {
        viewModel.intent(CountEvent.AttemptMagic)
        Thread.sleep(1010)
        assertEquals(1000, currentState.count)
    }

    @Test
    fun totalTest() {
        listOf(CountEvent.Increase, CountEvent.Decrease, CountEvent.Decrease) expectState {
            assertEquals(-1, it.count)
        }
    }

    @Test
    fun testWithCurrentState() {
        viewModel.intent(CountEvent.Increase)
        assertEquals(1, currentState.count)
    }
}