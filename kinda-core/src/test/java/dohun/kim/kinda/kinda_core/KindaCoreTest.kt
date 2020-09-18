package dohun.kim.kinda.kinda_core

import kotlinx.coroutines.GlobalScope
import org.junit.Assert.assertEquals
import org.junit.Test

class KindaCoreTest {

    @Test(expected = IllegalStateException::class)
    fun `Builder NullIncluded ThrowIllegalStateException`() {
        Kinda.Builder<CountState, CountEvent, CountSideEffect>()
            .initialState(CountState())
            .build()
    }

    @Test
    fun `Increase Count=1`() {
        var latestCount: Int? = null
        val countKinda = Kinda.Builder<CountState, CountEvent, CountSideEffect>()
            .initialState(CountState())
            .coroutineScope(GlobalScope)
            .reducer(CountReducer())
            .sideEffectHandler(CountSideEffectHandler())
            .render { state ->
                latestCount = state.count
            }
            .build()

        countKinda.intent(CountEvent.Increase)

        assertEquals(latestCount, 1)
    }

    @Test
    fun `Side Effect thread sleep 1100`() {
        var latestCount: Int? = null
        val countKinda = Kinda.Builder<CountState, CountEvent, CountSideEffect>()
            .initialState(CountState())
            .coroutineScope(GlobalScope)
            .reducer(CountReducer())
            .sideEffectHandler(CountSideEffectHandler())
            .render { state ->
                latestCount = state.count
                print("count: ${state.count} ")
            }
            .build()

        countKinda.intent(CountEvent.Magic)

        Thread.sleep(1100)

        assertEquals(latestCount, 1000)
    }

    @Test
    fun `Side Effect not waiting`() {
        var latestCount: Int? = null
        val countKinda = Kinda.Builder<CountState, CountEvent, CountSideEffect>()
            .initialState(CountState())
            .coroutineScope(GlobalScope)
            .reducer(CountReducer())
            .sideEffectHandler(CountSideEffectHandler())
            .render { state ->
                latestCount = state.count
                print("count: ${state.count} ")
            }
            .build()

        countKinda.intent(CountEvent.Magic)

        assertEquals(latestCount, null)
    }
}