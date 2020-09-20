package dohun.kim.kinda.kinda_dsl

import dohun.kim.kinda.kinda_core.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import org.junit.Test

data class CountState(
    val count: Int = 0,
    val toastEvent: Event<String> = Event()
) : KindaState

sealed class CountEvent : KindaEvent {
    object AttemptMagic : CountEvent()
    object Increase : CountEvent()
    object Decrease : CountEvent()
    object Increase1000 : CountEvent()
}

sealed class CountSideEffect : KindaSideEffect {
    object Magic : CountSideEffect()
}

class CountTest {

    @Test
    fun a() {
        val kinda = Kinda.Builder<CountState, CountEvent, CountSideEffect>()
            .coroutineScope(GlobalScope)
            .initialState(CountState())
            .reducer(
                buildReducer {
                    whenEvent(CountEvent.Increase) {
                        next(copy(count = count + 1))
                    }

                    whenEvent(CountEvent.Decrease) {
                        next(copy(count = count - 1))
                    }

                    whenEvent(CountEvent.AttemptMagic) {
                        dispatch(CountSideEffect.Magic)
                    }

                    whenEvent(CountEvent.Increase1000) {
                        next(copy(count = count + 1000))
                    }
                }
            ).sideEffectHandler(
                buildSideEffectHandler {
                    whenSideEffect(CountSideEffect.Magic) {
                        delay(1000)
                        CountEvent.Increase1000
                    }
                }
            ).render {
                println(it.count)
            }.build()

        kinda.intent(CountEvent.Decrease)
        kinda.intent(CountEvent.Decrease)
        kinda.intent(CountEvent.Increase)
        kinda.intent(CountEvent.AttemptMagic)
        kinda.intent(CountEvent.Increase)

        Thread.sleep(1100)
    }
}